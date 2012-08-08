package x.readr.api0.req.write;

import java.io.IOException;
import java.io.InputStream;

import x.readr.StreamIdUserLabel;
import x.readr.req.AbstractApi0JsonRequest;
import x.readr.req.AbstractAuthedConnection;
import x.readr.req.Params;


/**
 * Disable (Remove) one or multiple tag;
 * 
 * POST /reader/api/0/disable-tag
 * 
 * There may be multiple s and mo t
 */
public class DisableTag extends
        AbstractApi0JsonRequest<Boolean, AbstractAuthedConnection> {

    private final StreamIdUserLabel s;

    // private final String t;
    // private final Action ac;
    // public enum Action {
    // disable_tags;
    // @Override
    // public String toString() {
    // return toString().replace('_', '-');
    // }
    // }

    public DisableTag(StreamIdUserLabel tag, String token) {
        super(token);
        this.s = tag;
    }

    @Override
    protected Method getMethod() {
        return Method.POST;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/disable-tag";
    }

    @Override
    protected void getParams(Params params) {
        params.post.add("s", s);
    }

    @Override
    protected Boolean deserialize(InputStream in) throws IOException {
        return "OK".equals(read(in));
    }
}