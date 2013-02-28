package me.shakiba.readr.api0.params;

import me.shakiba.readr.req.AbstractRequest;
import me.shakiba.readr.req.RequestParam;

public class PsContent<T extends AbstractRequest<?, ?>> extends RequestParam<T> {
    public PsContent(T wrapper) {
        super(wrapper);
    }

    /**
     * Encoded list of possible sharers, for whom comments will be fetched.
     * Obtained from ApiFriendList. (none, CKfbjpeeFxgB)
     */
    public T setSharers(String sharers) {
        get("sharers", sharers);
        return wrapper;
    }

    /**
     * Whether or not to fetch likes (true)
     */
    public T setLikes(Boolean likes) {
        get("likes", likes);
        return wrapper;
    }

    /**
     * Whether or not to fetch comments (true)
     */
    public T setComments(Boolean comments) {
        get("comments", comments);
        return wrapper;
    }

    /**
     * Whether or not to translate the response into the user's display language
     * (false)
     */
    public T setTrans(Boolean trans) {
        get("trans", trans);
        return wrapper;
    }

    /**
     * Whether or not to include MediaRSS elements from the original feed in the
     * response (false)
     */
    public T setMediaRss(Boolean mediaRss) {
        get("mediaRss", mediaRss);
        return wrapper;
    }
}