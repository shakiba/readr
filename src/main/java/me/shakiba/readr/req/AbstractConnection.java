package me.shakiba.readr.req;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Set;

import me.shakiba.readr.req.AbstractRequest.Method;

import org.apache.log4j.Logger;

public abstract class AbstractConnection {
    public final <T> T execute(AbstractRequest<T, ?> req) {
        String url = req.getUrl0();
        Params params = req.getParams0();

        if (logger.isDebugEnabled()) {
            logger.debug("execute     " + req.getMethod() + " " + url);
            logger.debug("get  params " + params.gets());
            logger.debug("post params " + params.posts());
        }

        if (req.getMethod() == Method.GET) {
            return get(url, params, req);
        } else {
            return post(url, params, req);
        }
    }

    protected abstract <T> T get(String url, Params paramas,
            AbstractRequest<T, ?> req);

    protected abstract <T> T post(String url, Params paramas,
            AbstractRequest<T, ?> req);

    protected final <T> T response(InputStream stream, AbstractRequest<T, ?> req) {
        try {
            return req.deserialize0(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String addToUrl(String url, Set<Param> params) {
        if (params.isEmpty()) {
            return url;
        }
        // what about #fragment?
        return url + (url.indexOf('?') > -1 ? '&' : '?') + toQuery(params);
    }

    public static String toQuery(Collection<Param> params) {
        StringBuffer make = new StringBuffer();
        try {
            int i = 0;
            for (Param param : params) {
                if (i > 0) {
                    make.append('&');
                }
                make.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                make.append('=');
                if (param.getValue() != null) {
                    make.append(URLEncoder.encode(param.getValue().toString(),
                            "UTF-8"));
                }
                i++;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return make.toString();
    }

    private static Logger logger = Logger.getLogger(AbstractConnection.class);
}