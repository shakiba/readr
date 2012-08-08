package x.readr;

public class ItemIdLong extends ItemId {
    ItemIdLong(Long value) {
        super(value);
    }

    @Override
    public String toString() {
        return getLongForm();
    }
}
