package me.shakiba.readr.req;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractRequest<T, C extends AbstractConnection> {

    public static final String GOOGLE = "https://www.google.com";

    private final Set<RequestParam<?>> paramSets = new HashSet<RequestParam<?>>();

    // private final List<Param> params = new ArrayList<Param>();
    // protected void set(String key, Object value) {
    // params.add(new Param(key, value));
    // }

    private Deserializer<T> deserializer;

    public enum Method {
        POST, GET
    }

    void add(RequestParam<?>... paramsets) {
        for (RequestParam<?> ps : paramsets) {
            if (ps != null) {
                this.paramSets.add(ps);
            }
        }
    }

    public final String getUrl0() {
        return GOOGLE + getUrl();
    }

    public final Params getParams0() {
        Params params = new Params();
        for (RequestParam<?> ps : paramSets) {
            params.addAll(ps);
        }
        // params.addAll(this.params);
        getParams(params);
        return params;
    }

    public final AbstractRequest<T, C> setDeserializer(
            Deserializer<T> deserializer) throws IOException {
        this.deserializer = deserializer;
        return this;
    }

    public final T deserialize0(InputStream in) throws IOException {
        if (deserializer != null) {
            return deserializer.deserialize(in);
        } else {
            return deserialize(in);
        }
    }

    public T execute(C connection) {
        return connection.execute(this);
    }

    // TODO: merget with getParams?
    protected abstract Method getMethod();

    protected abstract String getUrl();

    protected abstract void getParams(Params params);

    protected abstract T deserialize(InputStream in) throws IOException;

    public interface Deserializer<T> {
        T deserialize(InputStream in) throws IOException;
    }

    public static String read(InputStream in) throws IOException {
        return read(new InputStreamReader(in, "UTF-8"));
    }

    public static String read(Reader reader) throws IOException {
        if (reader == null) {
            return null;
        }
        int size = 1024;
        char[] buffer = new char[size];
        StringBuilder string = new StringBuilder();
        int read = 0;
        while ((read = reader.read(buffer)) != -1) {
            string.append(buffer, 0, read);
        }
        return string.toString();
    }

    public static void writeTo(InputStream in, String file) throws IOException {
        writeTo(in, new FileOutputStream(file), 16);
    }

    public static void writeTo(InputStream in, OutputStream out, int sizeKB)
            throws IOException {
        sizeKB *= 1024;
        byte[] buffer = new byte[sizeKB];
        int read = 0;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    }

    public static Reader reader(InputStream in) throws IOException {
        return new InputStreamReader(in, "UTF-8");
    }

    public static String encoded(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}