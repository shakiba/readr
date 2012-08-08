package x.readr;

public class StreamIdUserLabel extends StreamIdUser {
    public StreamIdUserLabel(String label) {
        super("user/-/label/" + label);
    }

    public StreamIdUserLabel(UserId userId, String label) {
        super("user/" + userId.getUnsigned() + "/label/" + label);
    }

    public StreamIdUserLabel forUser(UserId userId) {
        String label = get().replaceFirst("user/(-|\\d{20})/label/", "");
        return new StreamIdUserLabel(userId, label);
    }
}