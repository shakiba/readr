package me.shakiba.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;

import me.shakiba.readr.api0.model.Api0TagList;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractAuthedConnection;
import me.shakiba.readr.req.Params;

/**
 * Get list of tags and shared status for each tag.
 * <p>
 * GET /reader/api/0/tag/list
 */
public class TagList extends
        AbstractApi0JsonRequest<Api0TagList, AbstractAuthedConnection> {

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/tag/list";
    }

    @Override
    protected void getParams(Params params) {
    }

    @Override
    protected Api0TagList deserialize(InputStream in) throws IOException {
        return deserialize(in, Api0TagList.class);
    }
}