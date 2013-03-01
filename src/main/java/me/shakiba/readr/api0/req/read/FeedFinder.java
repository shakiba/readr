package me.shakiba.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;

import me.shakiba.readr.api0.model.Api0FeedFind;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractConnection;
import me.shakiba.readr.req.Params;

/**
 * Authed? No.
 */
public class FeedFinder extends
        AbstractApi0JsonRequest<Api0FeedFind, AbstractConnection> {

    private final String query;

    public FeedFinder(String query) {
        this.query = query;
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/feed-finder";
    }

    @Override
    protected void getParams(Params params) {
        params.get.add("q", query);
    }

    @Override
    protected Api0FeedFind deserialize(InputStream in) throws IOException {
        return deserialize(in, Api0FeedFind.class);
    }
}