package x.readr.api0.params;

import x.readr.StreamId;
import x.readr.req.AbstractRequest;
import x.readr.req.RequestParam;

public class PsStream<T extends AbstractRequest<?, ?>> extends RequestParam<T> {

    public PsStream(T wrapper) {
        super(wrapper);
    }

    /**
     * Number of item IDs to return (up to a maximum of 10,000). When fetching
     * multiple streams, this many items will be returned from each stream,
     * unless the merge parameter is true. (none, but is required, 1000)
     */
    public T setNumber(int n) {
        get("n", n);
        return wrapper;
    }

    /**
     * Continuation token. If a response does not represent all items in a
     * timestamp range, it will have a continuation attribute. The same request
     * can be re-issued with the value of that attribute put in this parameter
     * to get more items. (none, CJLRnpeNpakC)
     */
    public T setContinuation(String c) {
        get("c", c);
        return wrapper;
    }

    /**
     * Tag to exclude. Items that have this tag will not be in the response.
     * Commonly used to hide read items (i.e. items with the read tag) (none,
     * user/-/state/com.google/read)
     */
    public T setExcludeTags(StreamId... xt) {
        get("xt", xt);
        return wrapper;
    }

    /**
     * Timestamp (in seconds since the epoch) of the newest item that you're
     * interested in (none,1309018028)
     * 
     * @return
     */
    public T setNewestTime(Integer nt) {
        get("nt", nt);
        return wrapper;
    }

    /**
     * Timestamp (in seconds since the epoch) of the oldest item that you're
     * interested in (none, 1306426059)
     */
    public T setOldestTime(Integer ot) {
        get("ot", ot);
        return wrapper;
    }

    /**
     * Ranking method. Possible values are: n: newest-first (reverse
     * chronological), o: oldest-first (chronological), a: "magic", c: items
     * with most recent commens first (n)
     */
    public T setRankingMethod(Character r) {
        get("r", r);
        return wrapper;
    }
}