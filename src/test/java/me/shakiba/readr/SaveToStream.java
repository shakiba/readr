package me.shakiba.readr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.shakiba.readr.req.AbstractRequest;
import me.shakiba.readr.req.AbstractRequest.Deserializer;

public class SaveToStream<T> implements Deserializer<T> {
    private final OutputStream out;

    public SaveToStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public T deserialize(InputStream in) throws IOException {
        AbstractRequest.writeTo(in, out, 4);
        return null;
    }
}