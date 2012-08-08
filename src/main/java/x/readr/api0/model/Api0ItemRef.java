package x.readr.api0.model;

import java.util.List;

import x.readr.ItemId;

public class Api0ItemRef {

    /**
     * Reader internal timestamp of this item in "nanoseconds". (Not item
     * publish/update time.)
     */
    public long timestampUsec;

    public ItemId id;

    public List<String> directStreamIds;
}