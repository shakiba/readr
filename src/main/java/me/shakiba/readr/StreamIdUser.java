package me.shakiba.readr;

public abstract class StreamIdUser extends StreamId {
    protected StreamIdUser(String id) {
        super(id);
    }

    public static UserId extractUser(StreamId id) {
        String[] split = id.get().split("\\/", 3);
        if (split.length > 1) {
            return UserId.fromUnsigned(split[1]);
        }
        return null;
    }
}
