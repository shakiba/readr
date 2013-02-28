package me.shakiba.readr;

public class StreamIdUserState extends StreamIdUser {

    public StreamIdUserState(State state) {
        this("-", state.toString());
    }

    public StreamIdUserState(UserId userId, State state) {
        this(userId.getUnsigned(), state.toString());
    }

    private StreamIdUserState(String userId, String state) {
        super("user/" + userId + "/state/com.google/" + state);
    }

    public StreamIdUserState forUser(UserId userId) {
        String state = get().replaceFirst("user/(-|\\d{20})/state/com.google/",
                "");
        return new StreamIdUserState(userId.getUnsigned(), state);
    }

    public static enum State {
        read("read"),

        kept_unread("kept-unread"),

        fresh("fresh"),

        starred("starred"),

        broadcast("broadcast"),

        like("like"),

        reading_list("reading-list"),

        tracking_body_link_used("tracking-body-link-used"),

        tracking_emailed("tracking-emailed"),

        tracking_item_link_used("tracking-item-link-used"),

        tracking_kept_unread("tracking-kept-unread");

        private final String name;

        private State(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * A read item will have the state read
     */
    public static StreamIdUserState read = new StreamIdUserState(State.read);

    /**
     * Once you've clicked on "keep unread", an item will have the state
     * kept-unread
     */
    public static StreamIdUserState kept_unread = new StreamIdUserState(
            State.kept_unread);

    /**
     * When a new item of one of your feeds arrive, it's labeled as fresh. When
     * (need to find what remove fresh label), the fresh label disappear.
     */
    public static StreamIdUserState fresh = new StreamIdUserState(State.fresh);

    /**
     * When your mark an item with a star, you set it's starred state
     */
    public static StreamIdUserState starred = new StreamIdUserState(
            State.starred);

    /**
     * When your mark an item as being public (when you share an item), you set
     * it's broadcast state
     */
    public static StreamIdUserState broadcast = new StreamIdUserState(
            State.broadcast);

    public static StreamIdUserState like = new StreamIdUserState(State.like);

    /**
     * All you items are flagged with the reading-list state. To see all your
     * items, just ask for items in the state reading-list
     */
    public static StreamIdUserState reading_list = new StreamIdUserState(
            State.reading_list);

    /**
     * Set if you ever clicked on a link in the description of the item.
     */
    public static StreamIdUserState tracking_body_link_used = new StreamIdUserState(
            State.tracking_body_link_used);

    /**
     * Set if you ever emailed the item to someone.
     */
    public static StreamIdUserState tracking_emailed = new StreamIdUserState(
            State.tracking_emailed);

    /**
     * Set if you ever clicked on a link in the description of the item.
     */
    public static StreamIdUserState tracking_item_link_used = new StreamIdUserState(
            State.tracking_item_link_used);

    /** Set if you ever mark your read item as unread. */
    public static StreamIdUserState tracking_kept_unread = new StreamIdUserState(
            State.tracking_kept_unread);
}
