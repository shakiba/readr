package me.shakiba.readr.api0.model;

import java.util.ArrayList;
import java.util.List;

import me.shakiba.readr.model.StreamId;

public class Api0Stream {

    public String direction;
    public StreamId id;
    public String title;
    public String description;
    public String continuation;
    public List<Api0Link> self;
    public List<Api0Link> alternate;
    public long updated;
    public List<Api0Item> items = new ArrayList<Api0Item>();

    public String alternate() {
        if (alternate != null && !alternate.isEmpty()) {
            return alternate.get(0).href;
        }
        return null;
    }

}