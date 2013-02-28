package me.shakiba.readr.req;

import java.io.InputStream;

public class StreamConnection extends AbstractConnection {

    private final InputStream stream;

    public StreamConnection(InputStream file) {
        this.stream = file;
    }

    @Override
    protected <T> T post(String url, Params paramas,
            AbstractRequest<T, ?> callback) {
        return response(stream, callback);
    }

    @Override
    protected <T> T get(String url, Params paramas,
            AbstractRequest<T, ?> callback) {
        return response(stream, callback);
    }
}