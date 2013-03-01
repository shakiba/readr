package me.shakiba.readr;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import me.shakiba.readr.api0.req.read.ActionToken;
import me.shakiba.readr.api0.req.read.WhoAmI;
import me.shakiba.readr.atom.model.Feed;
import me.shakiba.readr.model.ItemId;
import me.shakiba.readr.model.StreamId;
import me.shakiba.readr.model.UserId;
import me.shakiba.readr.req.AbstractApi0JsonRequest.ItemIdTypeAdapter;
import me.shakiba.readr.req.AbstractApi0JsonRequest.StreamIdTypeAdapter;
import me.shakiba.readr.req.AbstractConnection;
import me.shakiba.readr.req.ApacheHttpClient4Connection;
import me.shakiba.readr.req.OAuthSribeConnection;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.Token;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GenericRequestTest {
    private static OAuthUtil oautil = new OAuthUtil().load(false);

    protected static OAuthSribeConnection oauth = new OAuthSribeConnection(
            new ServiceBuilder().provider(GoogleApi.class).apiKey(oautil.key)
                    .apiSecret(oautil.secret).build(), new Token(
                    oautil.accToken, oautil.accSecret));

    private static Supplier<String> actionToken = Suppliers
            .memoize(new Supplier<String>() {
                @Override
                public String get() {
                    return new ActionToken().execute(oauth);
                }
            });

    private static Supplier<UserId> me = Suppliers
            .memoize(new Supplier<UserId>() {
                @Override
                public UserId get() {
                    return new WhoAmI().execute(oauth);
                }
            });

    protected static AbstractConnection simple = new ApacheHttpClient4Connection(
            2);

    protected static Gson gson = new GsonBuilder()
            .registerTypeAdapter(ItemId.class, new ItemIdTypeAdapter())
            .registerTypeAdapter(StreamId.class, new StreamIdTypeAdapter())
            .setPrettyPrinting().create();

    protected static Marshaller marshaller;

    static {
        try {
            JAXBContext jc = JAXBContext.newInstance(Feed.class.getPackage()
                    .getName());
            marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            Throwables.propagate(e);
        }
    }

    protected final static String actionToken() {
        return actionToken.get();
    }

    protected final static UserId me() {
        return me.get();
    }
}