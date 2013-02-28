package me.shakiba.readr.req;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Params {
    public final ParamSet get = new ParamSet();
    public final ParamSet post = new ParamSet();

    public Set<Param> gets() {
        return get.list();
    }

    public Set<Param> posts() {
        return post.list();
    }

    public void get(String name, Object value) {
        get.add(name, value);
    }

    public void post(String name, Object value) {
        post.add(name, value);
    }

    public void addAll(Params params) {
        if (params != null) {
            post.list.addAll(params.post.list);
            get.list.addAll(params.get.list);
        }
    }

    public class ParamSet {
        private Set<Param> list = new HashSet<Param>();

        private ParamSet() {
        }

        private Set<Param> list() {
            return list;
        }

        public void add(String name, Object value) {
            if (value != null) {
                list.add(new Param(name, value));
            }
        }

        public <E> void addAll(Collection<Entry<String, E>> values) {
            if (values != null) {
                for (Entry<String, E> e : values) {
                    add(e.getKey(), /* to.toString */(e.getValue()));
                }
            }
        }

        public <E> void addAll(String name, Collection<E> values) {
            if (values != null) {
                for (E value : values) {
                    add(name, /* to.toString */(value));
                }
            }
        }

        public <E> void addAll(String name, E... values) {
            if (values != null) {
                for (E value : values) {
                    add(name, value);
                }
            }
        }
    }

    public interface ToString<E> {
        String toString(E obj);
    }
}