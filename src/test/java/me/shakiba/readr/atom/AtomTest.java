package me.shakiba.readr.atom;

import me.shakiba.readr.GenericRequestTest;
import me.shakiba.readr.Sample;
import me.shakiba.readr.atom.model.Feed;
import me.shakiba.readr.atom.req.AtomStream;
import me.shakiba.readr.model.StreamId;

import org.testng.annotations.Test;

@Test
public class AtomTest extends GenericRequestTest {

    public void test() throws Exception {
        Feed feed = new AtomStream(StreamId.feed(Sample.feed)).psStream
                .setNumber(2).execute(oauth);
        marshaller.marshal(feed, System.out);
    }
}