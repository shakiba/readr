package me.shakiba.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;

import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractAuthedConnection;
import me.shakiba.readr.req.Params;



/**
 * To protect against XSRF attacks, all state-changing methods require an action
 * token in addition to an authentication token. The token can be fetched by
 * making a GET request for /reader/api/0/token and should be passed in to
 * state-changing requests (generally POST requests) with the T parameter (see
 * ApiCommonInputs).
 * <p>
 * The token is valid for 30 minutes. If it is missing or invalid, a 401 HTTP
 * response code will be given and the response will have a
 * X-Reader-Google-Bad-Token: true HTTP header.
 * <p>
 * GET /reader/api/0/token
 */
public class ActionToken extends
        AbstractApi0JsonRequest<String, AbstractAuthedConnection> {

    public ActionToken() {
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/token";
    }

    @Override
    protected void getParams(Params params) {
    }

    @Override
    protected String deserialize(InputStream in) throws IOException {
        return read(in);
    }
}