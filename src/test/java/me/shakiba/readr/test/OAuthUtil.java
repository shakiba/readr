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
    }

    public OAuthUtil load(boolean prompt) {
        try {
            if (!loadConsumer(prompt)) {
                throw new RuntimeException(
                        "Missing consumer key and/or secret.");
            }
            if (!loadAccess(prompt)) {
                throw new RuntimeException(
                        "Missing access token and/or secret.");
            }

            System.out.println(key);
            System.out.println(secret);
            System.out.println(accToken);
            System.out.println(accSecret);

            // try {
            // System.out.println(Arrays.toString(node.childrenNames()));
            // } catch (BackingStoreException e) {
            // e.printStackTrace();
            // }
            oauth = new ServiceBuilder().provider(GoogleApi.class).apiKey(key)
                    .apiSecret(secret).scope(OAuthSribeConnection.SCOPE)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private boolean loadConsumer(boolean prompt) throws IOException {
        Preferences node = Preferences.userRoot().node(NODE);
        if (node == null) {
            if (prompt) {
                saveConsumer(readLine("Enter consumer key:").trim(),
                        readLine("Enter consumer secret:").trim());
            } else {
                return false;
            }
        }
        key = node.get(CONSUMER_KEY, null);
        secret = node.get(CONSUMER_SECRET, null);
        if (key == null || secret == null) {
            return false;
        }
        return true;
    }

    private boolean loadAccess(boolean prompt) throws IOException {
        Preferences node = Preferences.userRoot().node(NODE);
        if (node == null) {
            if (prompt) {
                Token requestToken = oauth.getRequestToken();
                System.out.println("Request token: " + requestToken);
                System.out.println("Auth url: "
                        + oauth.getAuthorizationUrl(requestToken));

                String verifier = readLine("Visit auth url and enter verifier:")
                        .trim();
                Token accessToken = oauth.getAccessToken(requestToken,
                        new Verifier(verifier));

                System.out.println("access_token=" + accessToken.getToken());
                System.out.println("access_secret=" + accessToken.getSecret());

                saveAccess(accessToken.getToken(), accessToken.getSecret());
            } else {
                return false;
            }
        }
        accToken = node.get(ACCESS_TOKEN, null);
        accSecret = node.get(ACCESS_SECRET, null);
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

    private static String readLine(String msg) throws IOException {
        System.out.println(msg);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        return reader.readLine();
    }
}