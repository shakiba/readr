package me.shakiba.readr.atom.req;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBException;

import me.shakiba.readr.StreamId;
import me.shakiba.readr.api0.params.PsStream;
import me.shakiba.readr.atom.model.Feed;
import me.shakiba.readr.req.AbstractAtomRequest;
import me.shakiba.readr.req.AbstractAuthedConnection;
import me.shakiba.readr.req.Params;

public class AtomStream extends
        AbstractAtomRequest<Feed, AbstractAuthedConnection> {

    private final StreamId stream;

    public final PsStream<AtomStream> psStream = new PsStream<AtomStream>(this);

    public AtomStream(StreamId stream) {
        this.stream = stream;
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/atom/" + encoded(stream.get());
    }

    @Override
    protected void getParams(Params params) {
    }

    @Override
    protected Feed deserialize(InputStream in) throws IOException {
        try {
            return (Feed) unmarshaller.unmarshal(new InputStreamReader(in,
                    "UTF-8"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}