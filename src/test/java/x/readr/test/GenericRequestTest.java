package x.readr.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.Token;

import x.readr.ItemId;
import x.readr.StreamId;
import x.readr.UserId;
import x.readr.api0.req.read.ActionToken;
import x.readr.api0.req.read.WhoAmI;
import x.readr.atom.model.Feed;
import x.readr.req.AbstractApi0JsonRequest.ItemIdTypeAdapter;
import x.readr.req.AbstractApi0JsonRequest.StreamIdTypeAdapter;
import x.readr.req.AbstractConnection;
import x.readr.req.ApacheHttpClient4Connection;
import x.readr.req.OAuthSribeConnection;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GenericRequestTest {

    protected static OAuthSribeConnection oauth = new OAuthSribeConnection(
            new ServiceBuilder().provider(GoogleApi.class)
                    .apiKey(OAuthUtil.key).apiSecret(OAuthUtil.secret).build(),
            new Token(OAuthUtil.accToken, OAuthUtil.accSecret));

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

    protected static AbstractConnection simple = new ApacheHttpClient4Connection(2);

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