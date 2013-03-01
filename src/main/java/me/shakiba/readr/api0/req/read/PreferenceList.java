package me.shakiba.readr.api0.req.read;

import java.io.IOException;
import java.io.InputStream;

import me.shakiba.readr.api0.model.Api0Prefs;
import me.shakiba.readr.req.AbstractApi0JsonRequest;
import me.shakiba.readr.req.AbstractAuthedConnection;
import me.shakiba.readr.req.Params;

/**
 * Get the preference list (configuration of the account for GoogleReader).
 * <p>
 * GET /reader/api/0/preference/list
 */
public class PreferenceList extends
        AbstractApi0JsonRequest<Api0Prefs, AbstractAuthedConnection> {

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected String getUrl() {
        return "/reader/api/0/preference/list";
    }

    @Override
    protected void getParams(Params params) {
    }

    @Override
    protected Api0Prefs deserialize(InputStream in) throws IOException {
        return deserialize(in, Api0Prefs.class);
    }
}