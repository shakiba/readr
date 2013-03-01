package me.shakiba.readr.model;

public class ItemIdShort extends ItemId {
    ItemIdShort(Long value) {
        super(value);
    }

    @Override
    public String toString() {
        return getShortForm();
    }
}