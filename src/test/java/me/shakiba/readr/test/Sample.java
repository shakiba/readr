package me.shakiba.readr.test;

import me.shakiba.readr.model.ItemId;
import me.shakiba.readr.model.StreamId;
import me.shakiba.readr.model.StreamIdFeed;

public class Sample {
    public final static StreamIdFeed feed = StreamId
            .feed("http://www.feedforall.com/sample.xml");

    public static ItemId _99 = null;
    // ItemId.fromAny("tag:google.com,2005:reader/item/");
    public static ItemId _98 = null;
    // ItemId.fromAny("tag:google.com,2005:reader/item/");
}
