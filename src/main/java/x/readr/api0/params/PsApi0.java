package x.readr.api0.params;

import x.readr.req.AbstractRequest;
import x.readr.req.RequestParam;

public class PsApi0<T extends AbstractRequest<?, ?>> extends RequestParam<T> {

    public PsApi0(T wrapper) {
        super(wrapper);
    }

    /**
     * Identifier for the program making the API requests. Especially meant for
     * browser-based applications that do not control the user agent.
     */
    public T setClient(String client) {
        get("client", client);
        return wrapper;
    }

    /**
     * Output format. Possible values are json (JavaScript Object Notation) and
     * xml (an XML representation of JSON).
     */
    public T setOutput(String output) {
        get("output", output);
        return wrapper;
    }

    /**
     * ActionToken (required for POST requests)
     */
    public T setActionToken(String T) {
        get("T", T);
        return wrapper;
    }

    /**
     * Language to use for the response. If not specified, will be inferred
     * based onAccept-Language HTTP header, IP, user preferences, etc. Example:
     * en
     */
    public T setHumanLanguage(String hl) {
        get("hl", hl);
        return wrapper;
    }
}