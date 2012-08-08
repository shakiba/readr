package x.readr.api0.model;

import java.util.ArrayList;
import java.util.List;

public class Api0FeedFind {

    long updated;
    String id;
    String title;
    public List<Api0FeedFind> self = new ArrayList<Api0FeedFind>();
    public List<Api0Link> feed = new ArrayList<Api0Link>();

}