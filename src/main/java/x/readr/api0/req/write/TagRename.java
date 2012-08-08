package x.readr.api0.req.write;

import java.io.IOException;
import java.io.InputStream;

import x.readr.StreamIdUserLabel;
import x.readr.req.AbstractApi0JsonRequest;
import x.readr.req.AbstractAuthedConnection;
import x.readr.req.Params;


/**
 * Rename tag
 * 
 * POST /reader/api/0/rename-tag
 */
public class TagRename extends
        AbstractApi0JsonRequest<Boolean, AbstractAuthedConnection> {

    private final StreamIdUserLabel s;
    private final String t;
    private final StreamIdUserLabel dest;

    public TagRename(StreamIdUserLabel sourceTag, String sourceTitle,
            StreamIdUserLabel destTag, String token) {
        super(token);
        this.s = sourceTag;
        this.t = sourceTitle;
        this.dest = destTag;
    }

    @Override
    protected Method getMethod() {
        return Method.POST;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/rename-tag";
    }

    @Override
    protected void getParams(Params params) {
        params.post.add("s", s);
        params.post.add("t", t);
        params.post.add("dest", dest);
    }

    @Override
    protected Boolean deserialize(InputStream in) throws IOException {
        return "OK".equals(read(in));
    }
}