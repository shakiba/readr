package me.shakiba.readr.api0.req.write;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.shakiba.readr.ItemId;
import me.shakiba.readr.StreamIdUser;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractAuthedConnection;
import me.shakiba.readr.req.Params;



/**
 * POST /reader/api/0/edit-tag
 */
public class EditTag extends
        AbstractApi0JsonRequest<Boolean, AbstractAuthedConnection> {

    private ItemId i;
    // private StreamId s;
    private List<StreamIdUser> a = new ArrayList<StreamIdUser>();
    private List<StreamIdUser> r = new ArrayList<StreamIdUser>();

    public EditTag(ItemId i, String token) {
        super(token);
        this.i = i;
    }

    public EditTag add(StreamIdUser add, StreamIdUser... more) {
        a.add(add);
        if (more.length > 0) {
            a.addAll(Arrays.asList(more));
        }
        return this;
    }

    public EditTag remove(StreamIdUser remove, StreamIdUser... more) {
        r.add(remove);
        if (more.length > 0) {
            r.addAll(Arrays.asList(remove));
        }
        return this;
    }

    @Override
    protected Method getMethod() {
        return Method.POST;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/edit-tag";
    }

    @Override
    protected void getParams(Params params) {
        params.post.addAll("a", a);
        params.post.addAll("r", r);
        params.post.add("i", i.getLongForm());
    }

    @Override
    protected Boolean deserialize(InputStream in) throws IOException {
        return "OK".equals(read(in));
    }
}