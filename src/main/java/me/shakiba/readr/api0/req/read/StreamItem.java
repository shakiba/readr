package me.shakiba.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import me.shakiba.readr.StreamId;
import me.shakiba.readr.api0.model.Api0StreamItemsId;
import me.shakiba.readr.api0.params.PsStream;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractConnection;
import me.shakiba.readr.req.Params;


/**
 * Given one or more StreamIds and fetching options, returns the IDs of the
 * items in those Streams. Getting just item IDs is significantly cheaper than
 * getting stream contents. If you need to do filtering of items, it is highly
 * encouraged to do this at the ID level before fetching item contents for the
 * subset of items that remain.
 * <p>
 * GET /reader/api/0/stream/items/ids
 * <p>
 * Authentication is optional. Public Streams don't require authentication, but
 * to request private streams or to see user-specific data (e.g. read state),
 * authentication is needed.
 */
public class StreamItem extends
        AbstractApi0JsonRequest<Api0StreamItemsId, AbstractConnection> {

    public final PsStream<StreamItem> psStream = new PsStream<StreamItem>(this);

    private Boolean merge;
    private Boolean includeAllDirectStreamIds;
    private List<StreamId> streams;

    /**
     * @param streams
     *            StreamId for which to fetch the item IDs. The parameter may be
     *            repeated to fetch the item IDs from multiple streams at once
     *            (more efficient from a backend perspective than multiple
     *            requests).
     * 
     * @param n
     *            See {@link PsStream#setNumber(int)}
     */
    public StreamItem(List<StreamId> streams, int n) {
        this.streams = streams;
        psStream.setNumber(n);
    }

    public StreamItem(StreamId streamId, int n) {
        this(Arrays.asList(new StreamId[] { streamId }), n);
    }

    /**
     * If true, the response will be combined into a single set of items before
     * ranking and n limit application. (false)
     * 
     * @return
     */
    public StreamItem setMerge(boolean merge) {
        this.merge = merge;
        return this;
    }

    /**
     * If true, all direct StreamIds will be included in the response. If false,
     * feed StreamIds will not be included. (false)
     * 
     * @return
     */
    public StreamItem setIncludeAllDirectStreamIds(
            boolean includeAllDirectStreamIds) {
        this.includeAllDirectStreamIds = includeAllDirectStreamIds;
        return this;
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/stream/items/ids";
    }

    @Override
    protected void getParams(Params params) {
        params.get.addAll("s", streams);
        params.get.add("merge", merge);
        params.get.add("includeAllDirectStreamIds", includeAllDirectStreamIds);
    }

    @Override
    protected Api0StreamItemsId deserialize(InputStream in) throws IOException {
        return deserialize(in, Api0StreamItemsId.class);
    }
}