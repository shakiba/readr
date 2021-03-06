package me.shakiba.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;

import me.shakiba.readr.api0.model.Api0SearchItem;
import me.shakiba.readr.model.StreamId;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractAuthedConnection;
import me.shakiba.readr.req.Params;

/**
 * Authed? Yes.
 */
public class SearchItemIds extends
        AbstractApi0JsonRequest<Api0SearchItem, AbstractAuthedConnection> {

    private final String query;
    private final StreamId streamId;

    public SearchItemIds(String query, StreamId streamId) {
        this.query = query;
        this.streamId = streamId;
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/search/items/ids";
    }

    @Override
    protected void getParams(Params params) {
        params.get.add("q", query);
        params.get.add("s", streamId.get());
    }

    @Override
    protected Api0SearchItem deserialize(InputStream in) throws IOException {
        return deserialize(in, Api0SearchItem.class);
    }
}