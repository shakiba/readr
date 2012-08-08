package x.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;

import x.readr.ItemId;
import x.readr.api0.model.Api0Likers;
import x.readr.req.AbstractApi0JsonRequest;
import x.readr.req.AbstractAuthedConnection;
import x.readr.req.Params;

/**
 * List of likers for an item
 * <p>
 * GET "/reader/api/0/item/likers"
 */
// Authed?!
public class ItemLikers extends
        AbstractApi0JsonRequest<Api0Likers, AbstractAuthedConnection> {

    private final ItemId i;

    public ItemLikers(ItemId item) {
        this.i = item;
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/item/likers";
    }

    @Override
    protected void getParams(Params params) {
        params.get.add("i", i.getLongForm());
    }

    @Override
    protected Api0Likers deserialize(InputStream in) throws IOException {
        return deserialize(in, Api0Likers.class);
    }
}