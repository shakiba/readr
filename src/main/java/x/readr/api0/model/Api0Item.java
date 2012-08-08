package x.readr.api0.model;

import java.util.List;

import x.readr.ItemId;
import x.readr.StreamId;

public class Api0Item {

    public long crawlTimeMsec;
    public long timestampUsec;

    public ItemId id;

    /**
     * seconds
     */
    public long published;

    /**
     * seconds
     */
    public long updated;

    public String title;
    public Api0Content content;
    public Api0Content summary;

    public String author;

    public Api0Source origin;

    public List<Api0Link> alternate;

    public List<Api0User> likingUsers;
    public List<Api0Comment> comments;
    public List<Api0Annotation> annotations;

    /**
     * Contains both feed categories and user tags. Feed categories are in form
     * of simple text but still encapsulated in StreamId object.
     */
    public List<StreamId> categories;

    public Api0Content getContent() {
        if (content != null) {
            return content;
        }
        if (summary != null) {
            return summary;
        }
        return null;
    }

    public String getDir() {
        Api0Content content = getContent();
        if (content != null) {
            return content.direction;
        }
        return "";
    }

    public String getAlternate() {
        if (alternate == null || alternate.isEmpty()) {
            return null;
        }
        return alternate.get(0).href;
    }

    // ['isReadStateLocked;
    // ['mediaGroup;['content;
    // ['mediaGroup;['content;[0]
    // ['mediaGroup;['content;[0]['url;
    // ['mediaGroup;['content;[1]
    // ['mediaGroup;['content;[1]['url;
}