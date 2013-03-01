package me.shakiba.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import me.shakiba.readr.api0.model.Api0Stream;
import me.shakiba.readr.model.StreamId;
import me.shakiba.readr.model.StreamIdUser;
import me.shakiba.readr.model.UserId;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractAuthedConnection;
import me.shakiba.readr.req.Params;

/**
 * A quick dirty hack to find current user id
 */
public class WhoAmI extends
        AbstractApi0JsonRequest<UserId, AbstractAuthedConnection> {

    public WhoAmI() {
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/stream/contents/"
                + encoded(StreamId.label(UUID.randomUUID().toString())
                        .toString());
    }

    protected void getParams(Params params) {
    }

    @Override
    protected UserId deserialize(InputStream in) throws IOException {
        Api0Stream execute = deserialize(in, Api0Stream.class);
        if (execute != null && execute.id != null) {
            return StreamIdUser.extractUser(execute.id);
        }
        return null;
    }
}