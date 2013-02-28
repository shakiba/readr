package me.shakiba.readr.req;

import org.apache.log4j.Logger;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class OAuthSribeConnection extends AbstractAuthedConnection {

    public static final String SCOPE = "https://www.google.com/reader/api/"
            + " https://www.google.com/reader/atom/";

    private final OAuthService oauth;

    private final Token token;

    public OAuthSribeConnection(OAuthService oauth, Token token) {
        this.oauth = oauth;
        this.token = token;
    }

    @Override
    public <T> T post(String url, Params params, AbstractRequest<T, ?> callback) {
        if (logger.isDebugEnabled()) {
            logger.debug(url);
        }
        OAuthRequest req = new OAuthRequest(Verb.POST, url);
        for (Param param : params.gets()) {
            req.addQuerystringParameter(param.getKey(), param.getValue()
                    .toString());
        }
        for (Param param : params.posts()) {
            req.addBodyParameter(param.getKey(), param.getValue().toString());
        }
        if (logger.isDebugEnabled()) {
            logger.debug(req.getBodyParams().asFormUrlEncodedString());
            logger.debug(params.gets());
            logger.debug(params.posts());
        }
        req.getBodyContents();
        oauth.signRequest(token, req);
        Response response = req.send();
        if (response.getCode() >= 300) {

        }
        return response(response.getStream(), callback);
    }

    @Override
    public <T> T get(String url, Params params,
            final AbstractRequest<T, ?> callback) {
        if (logger.isDebugEnabled()) {
            logger.debug(url);
        }
        OAuthRequest req = new OAuthRequest(Verb.GET, url);
        for (Param param : params.gets()) {
            req.addQuerystringParameter(param.getKey(), param.getValue()
                    .toString());
        }
        if (logger.isDebugEnabled()) {
            logger.debug(req.getQueryStringParams().asFormUrlEncodedString());
            logger.debug(params.gets());
        }

        oauth.signRequest(token, req);

        Response response = req.send();
        if (response.getCode() >= 300) {

        }
        return response(response.getStream(), callback);
    }

    private static Logger logger = Logger.getLogger(OAuthSribeConnection.class);
}