package x.readr.test;

import x.readr.ItemId;
import x.readr.StreamId;
import x.readr.StreamIdFeed;

public class Sample {
    public final static StreamIdFeed cnn_top = StreamId
            .feed("http://rss.cnn.com/rss/cnn_topstories.rss");

    // TODO: add sample item
    public static ItemId _99 = ItemId
            .fromAny("tag:google.com,2005:reader/item/");
    public static ItemId _98 = ItemId
            .fromAny("tag:google.com,2005:reader/item/");
}
