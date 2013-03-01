package me.shakiba.readr.req;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import me.shakiba.readr.ItemId;
import me.shakiba.readr.StreamId;
import me.shakiba.readr.api0.params.PsApi0;

import org.apache.log4j.Logger;

import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public abstract class AbstractApi0JsonRequest<T, C extends AbstractConnection>
        extends AbstractRequest<T, C> {

    public final PsApi0<AbstractApi0JsonRequest<T, C>> psApi0 = new PsApi0<AbstractApi0JsonRequest<T, C>>(
            this);

    public AbstractApi0JsonRequest() {
        psApi0.setOutput("json");
    }

    public AbstractApi0JsonRequest(String actionToken) {
        this();
        psApi0.setActionToken(actionToken);
    }

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ItemId.class, new ItemIdTypeAdapter())
            .registerTypeAdapter(StreamId.class, new StreamIdTypeAdapter())
            .create();

    public static class ItemIdTypeAdapter implements JsonDeserializer<ItemId>,
            JsonSerializer<ItemId> {
        @Override
        public ItemId deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            String id = json.getAsString();
            return ItemId.fromAny(id);
        }

        @Override
        public JsonElement serialize(ItemId src, Type typeOfSrc,
                JsonSerializationContext context) {
            return context.serialize(src.toString());
        }
    }

    public static class StreamIdTypeAdapter implements
            JsonDeserializer<StreamId>, JsonSerializer<StreamId> {
        @Override
        public StreamId deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            return StreamId.id(json.getAsString());
        }

        @Override
        public JsonElement serialize(StreamId src, Type typeOfSrc,
                JsonSerializationContext context) {
            return context.serialize(src.get());
        }
    }

    protected <Z> Z deserialize(InputStream in, Class<Z> clazz)
            throws IOException {
        String read = read(new InputStreamReader(in, "utf-8"));
        if (read == null) {
            read = "";
        }
        try {
            return gson.fromJson(read, clazz);
        } catch (Exception e) {
            logger.error(e + "\n" + read.replaceAll("\\s+", " "), e);
            throw Throwables.propagate(e);
        }
    }

    private static Logger logger = Logger
            .getLogger(AbstractApi0JsonRequest.class);

}