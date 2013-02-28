package me.shakiba.readr;

public class ItemIdLong extends ItemId {
    ItemIdLong(Long value) {
        super(value);
    }

    @Override
    public String toString() {
        return getLongForm();
    }
}
