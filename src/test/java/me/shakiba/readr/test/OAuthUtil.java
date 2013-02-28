package me.shakiba.readr.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import me.shakiba.readr.req.OAuthSribeConnection;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;



public class OAuthUtil {

    private static final String NODE = "/x/readr";
    private static final String CONSUMER_KEY = "consumer_key";
    private static final String CONSUMER_SECRET = "consumer_secret";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ACCESS_SECRET = "access_secret";

    public static final String key;
    public static final String secret;
    public static final String accToken;
    public static final String accSecret;

    public static final OAuthService oauth;
    static {
        Preferences node = Preferences.userRoot().node(NODE);
        if (node == null) {
            throw new RuntimeException("Missing Java (User) Preferences : "
                    + NODE);
        }
        key = node.get(CONSUMER_KEY, null);
        secret = node.get(CONSUMER_SECRET, null);
        accToken = node.get(ACCESS_TOKEN, null);
        accSecret = node.get(ACCESS_SECRET, null);

        System.out.println(key + "/" + secret);

        try {
            System.out.println(Arrays.toString(node.childrenNames()));
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }

        if (key == null || secret == null) {
            throw new RuntimeException("Missing Java (User) Preferences : "
                    + NODE + " " + CONSUMER_KEY + " " + CONSUMER_SECRET);
        }

        oauth = new ServiceBuilder().provider(GoogleApi.class).apiKey(key)
                .apiSecret(secret).scope(OAuthSribeConnection.SCOPE).build();
    }

    public static void main(String[] args) throws Exception {
        Token requestToken = oauth.getRequestToken();
        System.out.println(requestToken);
        System.out.println(oauth.getAuthorizationUrl(requestToken));
        System.out.println("Enter verifier:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String line = reader.readLine();
        Token accessToken = oauth.getAccessToken(requestToken, new Verifier(
                line.trim()));

        System.out.println("access_token=" + accessToken.getToken());
        System.out.println("access_secret=" + accessToken.getSecret());
        // try {
        // Preferences node = Preferences.userRoot().node(
        // "com/piqnt/readr");
        // node.put(ACCESS_TOKEN, accessToken.getToken());
        // node.put(ACCESS_SECRET, accessToken.getSecret());
        // node.flush();
        // System.out.println("Saved to user preferences.");
        // } catch (Exception e) {
        // System.out
        // .println("Unable to save to user preferences. Do it yourself!");
        // System.out.println(e);
        // }
        // OAuthRequest req = new OAuthRequest(Verb.GET,
        // "https://www.googleapis.com/userinfo/email");
        // req.addQuerystringParameter("alt", "json");
        // oauth.signRequest(accessToken, req);
        // Response response = req.send();
        // System.out.println(response.getBody());
    }

}