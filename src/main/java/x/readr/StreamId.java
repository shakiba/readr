package x.readr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * "Streams" refer to collections of items in the Google Reader API0. This
 * includes feeds, items with a specific tag, or folders. Stream IDs are are
 * string-based identifiers used to identify streams and are passed to many API0
 * methods.
 * 
 * <p>
 * Feed stream IDs
 * <p>
 * Streams that correspond to data that come from crawled feeds are of the form
 * feed/<feed URL>, for example:
 * <li>feed/http://googleblog.blogspot.com/atom.xml
 * <li>
 * feed/http://blogsearch.google.com/blogsearch_feeds?q=mihai%20parparita&hl=
 * en&scoring=d&num=10&output=atom
 * 
 * <p>
 * Tag stream IDs
 * <p>
 * Tags can be applied to items via the edit tag method and to subscriptions via
 * the edit subscription method. Once a tag is applied, the tag is available as
 * a stream, with the tag itself becoming the stream ID.
 * <p>
 * Tag stream IDs are of the form user/&lt;user ID>/&lt;tail>. &lt;user ID> is
 * the user's identifier, normally a numeric value obtained from the user info
 * method, but "-" may also be used for authenticated requests to signify the ID
 * of the authenticated user.
 * 
 * &lt;tail> is different depending on the kind of tag being used:
 * <p>
 * System tag stream IDs
 * <p>
 * System tags have a tail of the form state/com.google/<type>. Here are common
 * item-level tags:
 * <p>
 * <li>user/-/state/com.google/read: applied to read items
 * <li>user/-/state/com.google/kept-unread: applied to items that have been kept
 * unread, removed onc they are marked as read
 * <li>user/-/state/com.google/starred: applied to starred items
 * <li>user/-/state/com.google/broadcast: applied to shared items
 * <li>user/-/state/com.google/like: applied to liked items
 * 
 * <p>
 * Here are common subscription-level tags:
 * <p>
 * <li>user/-/state/com.google/reading-list: applied to all subscriptions,
 * corresponds to the "All items" view in the UI
 * <li>user/-/state/com.google/broadcast-friends: applied to the shared items of
 * the users that are being followed, subscriptions, corresponds to the
 * "People you follow" view in the UI
 * 
 * <p>
 * User-created tag stream IDs
 * <p>
 * User-created tags are added to items via the "Add tags" or "Edit tags" UI.
 * They are of the form user/-/label/<name>, for example:
 * <p>
 * <li>user/-/label/Foo
 * <li>user/-/label/Foo Bar
 * <li>user/-/label/Foo Bar™
 * 
 * <p>
 * Any character may be used as the name with the exception of: " ^ &lt; > ? & \
 * /,. Folder stream IDs
 * 
 * <p>
 * Folder stream IDs are the same as user-created tag stream IDs (i.e. they are
 * in the same namespace). Use in URLs
 * 
 * <p>
 * When used as query parameters, stream IDs should be escaped as usual. More
 * subtly, when used in paths (e.g. for the stream contents method) the stream
 * ID should be escaped too.
 * 
 * <p>
 * <li>Incorrect:
 * http://www.google.com/reader/api/0/stream/contents/feed/http://
 * example.com/search?q=foo
 * <li>Correct:
 * http://www.google.com/reader/api/0/stream/contents/feed%2Fhttp%3A
 * %2F%2Fwww.example.com%2Fsearch%3Fq%3Dfoo
 */
public class StreamId {

    private final String id;

    protected StreamId(String id) {
        this.id = id;
    }

    public boolean isFeed() {
        return id.startsWith("feed/");
    }

    public boolean isUser() {
        return id.startsWith("user/");
    }

    public String get() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!StreamId.class.isInstance(obj)) {
            return false;
        }
        StreamId that = (StreamId) obj;
        if (this.id.equals(that.id)) {
            return true;
        }
        Matcher m;
        Matcher n;
        if ((m = userStream.matcher(this.id)).find()
                && (n = userStream.matcher(that.id)).find()
                && (m.group(1).equals("-") || n.group(1).equals("-"))
                && m.group(2).equals(n.group(2))) {
            return true;
        }
        return false;
    }

    public static StreamId id(String id) {
        if (id == null) {
            return null;
        }
        return new StreamId(id);
    }

    public static StreamIdFeed feed(String url) {
        if (url == null) {
            return null;
        }
        return new StreamIdFeed(url);
    }

    public static StreamIdFeed feed(StreamId streamId) {
        if (streamId == null) {
            return null;
        }
        if (StreamIdFeed.class.isInstance(streamId)) {
            return (StreamIdFeed) streamId;
        }
        if (streamId.isFeed()) {
            return new StreamIdFeed(streamId.id);
        }
        throw new IllegalArgumentException(streamId.id + " is not feed!");
    }

    public static StreamIdUserLabel label(String label) {
        if (label == null) {
            return null;
        }
        return new StreamIdUserLabel(label);
    }

    /**
     * @return {@link #label(String)}
     */
    public static StreamIdUserLabel tag(String tag) {
        return label(tag);
    }

    private static final Pattern userStream = Pattern
            .compile("user/(-|\\d{20})/(.*)");

}