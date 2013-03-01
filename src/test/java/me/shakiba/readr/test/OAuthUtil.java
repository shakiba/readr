package me.shakiba.readr.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.prefs.Preferences;

import me.shakiba.readr.req.OAuthSribeConnection;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class OAuthUtil {

    private static final String NODE = "/me/shakiba/readr";
    private static final String CONSUMER_KEY = "consumer_key";
    private static final String CONSUMER_SECRET = "consumer_secret";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ACCESS_SECRET = "access_secret";

    public String key;
    public String secret;
    public String accToken;
    public String accSecret;

    public OAuthService oauth;

    public static void main(String[] args) throws Exception {
        new OAuthUtil().load(true);
        System.out.println("Everything seems ok!");
    }

    public OAuthUtil load(boolean prompt) {
        try {
            if (!loadConsumer(prompt)) {
                throw new RuntimeException(
                        "Missing consumer key and/or secret.");
            }
            oauth = new ServiceBuilder().provider(GoogleApi.class).apiKey(key)
                    .apiSecret(secret).scope(OAuthSribeConnection.SCOPE)
                    .build();
            if (!loadAccess(prompt)) {
                throw new RuntimeException(
                        "Missing access token and/or secret.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private boolean loadConsumer(boolean prompt) throws IOException {
        Preferences node = node();
        if (node == null || (key = node.get(CONSUMER_KEY, null)) == null
                || (secret = node.get(CONSUMER_SECRET, null)) == null) {
            if (prompt) {
                saveConsumer(key = readLine("Enter consumer key:").trim(),
                        secret = readLine("Enter consumer secret:").trim());
                node = node();
            } else {
                return false;
            }
        }
        if (key == null || secret == null) {
            return false;
        }
        return true;
    }

    private boolean loadAccess(boolean prompt) throws IOException {
        Preferences node = node();
        if (node == null || (accToken = node.get(ACCESS_TOKEN, null)) == null
                || (accSecret = node.get(ACCESS_SECRET, null)) == null) {
            if (prompt) {
                Token requestToken = oauth.getRequestToken();
                System.out.println("Request token: " + requestToken);
                System.out.println(oauth.getAuthorizationUrl(requestToken));

                String verifier = readLine("Visit auth url and enter verifier:")
                        .trim();
                Token accessToken = oauth.getAccessToken(requestToken,
                        new Verifier(verifier));
                key = accessToken.getToken();
                secret = accessToken.getSecret();

                System.out.println(ACCESS_TOKEN + "=" + key);
                System.out.println(ACCESS_SECRET + "=" + secret);

                saveAccess(accessToken.getToken(), accessToken.getSecret());
                node = node();
            } else {
                return false;
            }
        }

        if (accToken == null || accSecret == null) {
            return false;
        }
        return true;
    }

    private void saveConsumer(String key, String secret) {
        try {
            Preferences node = Preferences.userRoot().node(NODE);
            node.put(CONSUMER_KEY, key);
            node.put(CONSUMER_SECRET, secret);
            node.flush();
            System.out.println("Saved to user preferences.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Unable to save to user preferences!");
        }
    }

    private void saveAccess(String token, String secret) {
        try {
            Preferences node = Preferences.userRoot().node(NODE);
            node.put(ACCESS_TOKEN, token);
            node.put(ACCESS_SECRET, secret);
            node.flush();
            System.out.println("Saved to user preferences.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Unable to save to user preferences!");
        }
    }

    private Preferences node() {
        return Preferences.userRoot().node(NODE);
    }

    private static String readLine(String msg) throws IOException {
        System.out.println(msg);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        return reader.readLine();
    }
}