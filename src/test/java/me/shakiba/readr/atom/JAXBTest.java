package me.shakiba.readr.atom;

import java.io.FileReader;
import java.io.FileWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import me.shakiba.readr.atom.model.Feed;

public class JAXBTest {

    public static void main(String[] args) throws Exception {
        new JAXBTest().test();
    }

    public void test() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Feed.class.getPackage()
                .getName());
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Unmarshaller u = jc.createUnmarshaller();
        Feed feed = (Feed) u.unmarshal(new FileReader("feed0.xml"));
        m.marshal(feed, new FileWriter("feed1.xml"));
    }
}