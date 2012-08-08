package x.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import x.readr.ItemId;
import x.readr.api0.model.Api0Stream;
import x.readr.api0.params.PsContent;
import x.readr.req.AbstractApi0JsonRequest;
import x.readr.req.AbstractConnection;
import x.readr.req.Params;

/**
 * Given a collection of ItemIds and fetching options, returns the contents of
 * those items.
 * <p>
 * GET or POST /reader/api/0/stream/items/contents
 * <p>
 * This is not a state-changing servlet, but POST is supported in case so many
 * item IDs are passed in that URL length limits (2K) become an issue.
 * <p>
 * Authentication is optional. To see user-specific data (e.g. read state),
 * authentication is needed.
 */
public class ItemContent extends
        AbstractApi0JsonRequest<Api0Stream, AbstractConnection> {

    public final PsContent<ItemContent> psContent = new PsContent<ItemContent>(
            this);

    private final List<ItemId> i;

    private final boolean post;

    /**
     * @param ids
     *            ItemId for which to fetch the item contents. The parameter may
     *            be repeated to fetch the contents of multiple items (more
     *            efficient from a backend perspective than multiple requests).
     *            At least one value is required.
     */
    public ItemContent(boolean post, List<ItemId> ids) {
        this.post = post;
        this.i = ids;
    }

    public ItemContent(boolean post, ItemId id, ItemId... ids) {
        this.post = post;
        this.i = new ArrayList<ItemId>(ids.length + 1);
        this.i.add(id);
        for (ItemId itemId : ids) {
            this.i.add(itemId);
        }
    }

    @Override
    protected Method getMethod() {
        if (post) {
            return Method.POST;
        } else {
            return Method.GET;
        }
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/stream/items/contents";
    }

    @Override
    protected void getParams(Params params) {
        if (post) {
            for (ItemId ii : i) {
                params.post.add("i", ii.getShortForm());
            }
        } else {
            for (ItemId ii : i) {
                params.get.add("i", ii.getShortForm());
            }
        }
    }

    @Override
    protected Api0Stream deserialize(InputStream in) throws IOException {
        return deserialize(in, Api0Stream.class);
    }
}