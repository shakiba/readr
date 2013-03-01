package me.shakiba.readr.api0.model;

import java.util.List;

import me.shakiba.readr.StreamId;

public class Api0Subscription {

    public StreamId id;
    public String sortid;
    public String title;
    public long firstitemmsec;
    public String htmlUrl;
    List<Api0Category> categories;

}
