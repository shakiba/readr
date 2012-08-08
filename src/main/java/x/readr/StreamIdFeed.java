package x.readr;

public class StreamIdFeed extends StreamId {
    StreamIdFeed(String url) {
        super((url.startsWith("feed/") ? "" : "feed/") + url);
    }

    public String getFeedUrl() {
        return this.get().substring(5);
    }
}
