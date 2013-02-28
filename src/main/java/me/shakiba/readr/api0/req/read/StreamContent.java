package me.shakiba.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;

import me.shakiba.readr.StreamId;
import me.shakiba.readr.api0.model.Api0Stream;
import me.shakiba.readr.api0.params.PsContent;
import me.shakiba.readr.api0.params.PsStream;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractConnection;
import me.shakiba.readr.req.Params;


/**
 * Given a StreamId and fetching options, returns the contents of the items in
 * that Stream.
 * <p>
 * GET /reader/api/0/stream/contents/<escaped StreamId>
 * <p>
 * Authentication optional. Public Streams don't require authentication, but to
 * request private streams or to see user-specific data (e.g. read state),
 * authentication is needed.
 */
public class StreamContent extends
        AbstractApi0JsonRequest<Api0Stream, AbstractConnection> {

    public final PsContent<StreamContent> psContent = new PsContent<StreamContent>(
            this);
    public final PsStream<StreamContent> psStream = new PsStream<StreamContent>(
            this);

    private StreamId streamId;

    /**
     * @param streamId
     *            StreamId for which to fetch the item contents.
     */
    public StreamContent(StreamId streamId) {
        this.streamId = streamId;
    }

    public StreamContent(StreamId streamId, int n) {
        this.streamId = streamId;
        psStream.setNumber(n);
    }

    public StreamContent(StreamId streamId, String continuation) {
        this.streamId = streamId;
        psStream.setContinuation(continuation);
    }

    public StreamContent(StreamId streamId, String continuation, int n) {
        this.streamId = streamId;
        psStream.setContinuation(continuation);
        psStream.setNumber(n);
    }

    protected void getParams(Params paramsx) {
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/stream/contents/" + encoded(streamId.get());
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected Api0Stream deserialize(InputStream in) throws IOException {
        return deserialize(in, Api0Stream.class);
    }
}