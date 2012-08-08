package x.readr.api0.model;

import java.util.List;

import x.readr.StreamId;


public class Api0Subscription {
    public StreamId id;
    public String sortid;
    public String title;
    public long firstitemmsec;
    public String htmlUrl;
    List<Api0Category> categories;
}
