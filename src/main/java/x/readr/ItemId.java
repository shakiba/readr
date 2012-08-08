package x.readr;

import java.util.regex.Pattern;

/**
 * Items in Reader are referenced by globally unique item IDs. IDs are generally
 * derived from the <id> attribute in Atom feeds and the <guid> attribute in RSS
 * feeds. In the absence of those, the item URL may be used to generat the ID.
 * In cases where the feed does not provide IDs or URLs (or they are not deemed
 * "trustworthy", e.g. if more than one item in the feed response has the same
 * ID or URL), then the ID will be computed from a signature of certain feed
 * item properties (title, body, etc.).
 * 
 * Internally, item IDs are 64-bit numbers, and can be represented in API0
 * inputs and outputs in two forms:
 * 
 * Long form: The prefix tag:google.com,2005:reader/item/ followed by the ID as
 * an unsigned base 16 number that is 0-padded so that it's always 16 characters
 * wide.
 * 
 * Short form: The ID as a signed base 10 number.
 * 
 * Here's some sample mappings between the two forms:
 * 
 * tag:google.com,2005:reader/item/5d0cfa30041d4348 6705009029382226760
 * 
 * tag:google.com,2005:reader/item/024025978b5e50d2 162170919393841362 Long form
 * needs 0-padding
 * 
 * tag:google.com,2005:reader/item/fb115bd6d34a8e9f -355401917359550817 Short
 * form ends up being negative
 * 
 * All API0 methods that take item IDs accept either form, but different outputs
 * will contain different forms (for historical reasons).
 * 
 * If at all possible, you should not attempt to do conversions between forms.
 * Store IDs from responses as string blobs, and pass them back as is to other
 * API0 methods.
 * 
 */
public abstract class ItemId {
    private long id;

    ItemId(Long value) {
        this.id = value;
    }

    public String getLongForm() {
        return String.format("tag:google.com,2005:reader/item/%016x", id);
    }

    public String getShortForm() {
        return id + "";
    }

    public Long getValue() {
        return id;
    }

    public ItemIdShort toShort() {
        return ItemIdShort.class.isInstance(this) ? (ItemIdShort) this
                : new ItemIdShort(getValue());
    }

    public ItemIdLong toLong() {
        return ItemIdLong.class.isInstance(this) ? (ItemIdLong) this
                : new ItemIdLong(getValue());
    }

    @Override
    public abstract String toString();

    @Override
    public int hashCode() {
        // Long#hashCode
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public boolean equals(Object obj) {
        return ItemId.class.isInstance(obj) && ((ItemId) obj).id == this.id;
    }

    public static ItemIdShort fromShort(String shortForm) {
        return new ItemIdShort(new Long(shortForm));
    }

    public static ItemIdLong fromLong(String longForm) {
        String hex = longForm.substring(32);
        return new ItemIdLong((Long.parseLong(hex.substring(0, 8), 16) << 32)
                | (Long.parseLong(hex.substring(8, 16), 16) << 0));
    }

    public static ItemId fromAny(String id) throws IllegalArgumentException {
        if (isShortForm(id)) {
            return fromShort(id);
        } else if (isLongForm(id)) {
            return fromLong(id);
        }
        throw new IllegalArgumentException(id);
    }

    public static boolean isShortForm(String id) {
        return shortPattern.matcher(id).matches();
    }

    public static boolean isLongForm(String id) {
        return longPattern.matcher(id).matches();
    }

    private static Pattern longPattern = Pattern
            .compile("tag\\:google\\.com\\,2005\\:reader\\/item\\/[0-9a-fA-F]{16}");

    private static Pattern shortPattern = Pattern.compile("\\-?[0-9]{1,19}");

}