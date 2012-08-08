package x.readr.req;

import java.util.AbstractMap;

public class Param extends AbstractMap.SimpleEntry<String, Object> implements
        Comparable<Param> {

    private static final long serialVersionUID = -6225746983196658146L;

    public Param(String key, Object value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValueString();
    }

    public String getValueString() {
        return (getValue() == null ? "" : getValue().toString());
    }

    @Override
    public int compareTo(Param that) {
        return this.getKey().compareTo(that.getKey());
    }
}