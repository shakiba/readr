package me.shakiba.readr.test;

import me.shakiba.readr.model.ItemId;
import me.shakiba.readr.model.StreamId;
import me.shakiba.readr.model.StreamIdFeed;

public class Sample {
    public final static StreamIdFeed feed = StreamId
            .feed("http://www.feedforall.com/sample.xml");

    public static ItemId item1 = ItemId
            .fromAny("tag:google.com,2005:reader/item/6c65cd3e03006d21");
    public static ItemId item2 = ItemId
            .fromAny("tag:google.com,2005:reader/item/f245ed51f6116bfe");
}
