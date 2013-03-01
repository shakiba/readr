package me.shakiba.readr.req;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class ApacheHttpClient4Connection extends AbstractConnection {
    private HttpClient httpclient;

    public ApacheHttpClient4Connection(int maxConncetions) {
        this.httpclient = new DefaultHttpClient();
    }

    @Override
    public <T> T post(String url, Params params, AbstractRequest<T, ?> callback) {
        url = addToUrl(url, params.gets());

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Param param : params.posts()) {
            pairs.add(new BasicNameValuePair(param.getKey(), param
                    .getValueString()));
        }

        HttpPost post = new HttpPost(url);
        try {
            post.setEntity(new UrlEncodedFormEntity(pairs));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        T execute = execute(post, callback);
        return execute;
    }

    @Override
    public <T> T get(String url, Params params, AbstractRequest<T, ?> callback) {
        url = addToUrl(url, params.gets());
        HttpGet get = new HttpGet(url);
        T execute = execute(get, callback);
        return execute;
    }

    public <T> T execute(HttpUriRequest req,
            final AbstractRequest<T, ?> callback) {
        try {
            return httpclient.execute(req, new ResponseHandler<T>() {
                @Override
                public T handleResponse(HttpResponse response)
                        throws ClientProtocolException, IOException {
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() >= 300) {
                        throw new HttpResponseException(statusLine
                                .getStatusCode(), statusLine.getReasonPhrase());
                    }
                    T respose = response(response.getEntity().getContent(),
                            callback);
                    return respose;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}