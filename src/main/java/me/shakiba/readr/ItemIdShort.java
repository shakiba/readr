package me.shakiba.readr;

public class ItemIdShort extends ItemId {
    ItemIdShort(Long value) {
        super(value);
    }

    @Override
    public String toString() {
        return getShortForm();
    }
}