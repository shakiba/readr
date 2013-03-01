package me.shakiba.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;

import me.shakiba.readr.api0.model.Api0SubscriptionList;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractAuthedConnection;
import me.shakiba.readr.req.Params;

/**
 * Get the subscription list and shared status for each tag.
 * <p>
 * GET /reader/api/0/subscription/list
 */
public class SubscriptionList extends
        AbstractApi0JsonRequest<Api0SubscriptionList, AbstractAuthedConnection> {

    public SubscriptionList() {
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/subscription/list";
    }

    @Override
    protected void getParams(Params params) {
    }

    @Override
    protected Api0SubscriptionList deserialize(InputStream in)
            throws IOException {
        return deserialize(in, Api0SubscriptionList.class);
    }
}