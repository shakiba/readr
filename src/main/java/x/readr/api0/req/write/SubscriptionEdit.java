package x.readr.api0.req.write;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import x.readr.StreamIdFeed;
import x.readr.StreamIdUserLabel;
import x.readr.req.AbstractApi0JsonRequest;
import x.readr.req.AbstractAuthedConnection;
import x.readr.req.Params;


/**
 * POST /reader/api/0/subscription/edit
 */
public class SubscriptionEdit extends
        AbstractApi0JsonRequest<Boolean, AbstractAuthedConnection> {

    private final Action ac;
    private final StreamIdFeed s;
    private String t;
    private final List<StreamIdUserLabel> a = new ArrayList<StreamIdUserLabel>();
    private final List<StreamIdUserLabel> r = new ArrayList<StreamIdUserLabel>();

    public enum Action {
        edit, subscribe, unsubscribe;
    }

    public SubscriptionEdit(StreamIdFeed streamId, Action action, String token) {
        super(token);
        this.s = streamId;
        this.ac = action;
    }

    /**
     * Use with {@link Action#edit}
     */
    public SubscriptionEdit setTitle(String title) {
        t = title;
        return this;
    }

    /**
     * Use with {@link Action#edit}
     */
    public SubscriptionEdit add(StreamIdUserLabel add,
            StreamIdUserLabel... more) {
        a.add(add);
        if (more.length > 0) {
            a.addAll(Arrays.asList(add));
        }
        return this;
    }

    /**
     * Use with {@link Action#edit}
     */
    public SubscriptionEdit remove(StreamIdUserLabel remove,
            StreamIdUserLabel... more) {
        r.add(remove);
        if (more.length > 0) {
            r.addAll(Arrays.asList(more));
        }
        return this;
    }

    @Override
    protected Method getMethod() {
        return Method.POST;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/subscription/edit";
    }

    @Override
    protected void getParams(Params params) {
        params.post.add("s", s);
        params.post.add("ac", ac);
        params.post.add("t", t);
        params.post.addAll("a", a);
        params.post.addAll("r", r);
    }

    @Override
    protected Boolean deserialize(InputStream in) throws IOException {
        return "OK".equals(read(in));
    }
}