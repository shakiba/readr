package me.shakiba.readr.req;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import me.shakiba.readr.atom.model.Feed;

public abstract class AbstractAtomRequest<T, C extends AbstractConnection>
        extends AbstractRequest<T, C> {

    public static final JAXBContext jaxbContext;

    public static final Unmarshaller unmarshaller;

    static {
        JAXBContext jc = null;
        Unmarshaller um = null;
        try {
            jc = JAXBContext.newInstance(Feed.class.getPackage().getName());
            um = jc.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        jaxbContext = jc;
        unmarshaller = um;
    }
}