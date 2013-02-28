package me.shakiba.readr.api0.req.write;

import java.io.IOException;
import java.io.InputStream;

import me.shakiba.readr.StreamId;
import me.shakiba.readr.StreamIdUserLabel;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractAuthedConnection;
import me.shakiba.readr.req.Params;



/**
 * POST /reader/api/0/tag/edit
 */
public class TagEdit extends
        AbstractApi0JsonRequest<Boolean, AbstractAuthedConnection> {

    private final StreamId s;
    private final boolean pub;

    public TagEdit(StreamIdUserLabel s, boolean pub, String token) {
        super(token);
        this.s = s;
        this.pub = pub;
    }

    @Override
    protected Method getMethod() {
        return Method.POST;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/tag/edit";
    }

    @Override
    protected void getParams(Params params) {
        params.post.add("s", s);
        params.post.add("pub", pub);
    }

    @Override
    protected Boolean deserialize(InputStream in) throws IOException {
        return "OK".equals(read(in));
    }
}