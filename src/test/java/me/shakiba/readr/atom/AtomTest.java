package me.shakiba.readr.atom;

import me.shakiba.readr.StreamId;
import me.shakiba.readr.atom.model.Feed;
import me.shakiba.readr.atom.req.AtomStream;
import me.shakiba.readr.test.GenericRequestTest;
import me.shakiba.readr.test.Sample;

//@Test
public class AtomTest extends GenericRequestTest {

    public void test() throws Exception {
        Feed feed = new AtomStream(StreamId.feed(Sample.cnn_top)).psStream
                .setNumber(2).execute(oauth);
        marshaller.marshal(feed, System.out);
    }
}