package x.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;

import x.readr.api0.model.Api0UnreadList;
import x.readr.req.AbstractApi0JsonRequest;
import x.readr.req.AbstractAuthedConnection;
import x.readr.req.Params;


/**
 * Get all the information about where are located (in term of subscriptions and
 * tags/folders) the unread items.
 * <p>
 * GET /reader/api/0/unread-count
 * 
 * all=true or allcomments=true
 */
public class UnreadCount extends
        AbstractApi0JsonRequest<Api0UnreadList, AbstractAuthedConnection> {

    public UnreadCount() {
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/unread-count";
    }

    @Override
    protected void getParams(Params params) {
    }

    @Override
    protected Api0UnreadList deserialize(InputStream in) throws IOException {
        return deserialize(in, Api0UnreadList.class);
    }
}