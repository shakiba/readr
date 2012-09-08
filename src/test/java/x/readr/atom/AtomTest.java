package x.readr.atom;

import x.readr.StreamId;
import x.readr.atom.model.Feed;
import x.readr.atom.req.AtomStream;
import x.readr.test.GenericRequestTest;
import x.readr.test.Sample;

//@Test
public class AtomTest extends GenericRequestTest {

    public void test() throws Exception {
        Feed feed = new AtomStream(StreamId.feed(Sample.cnn_top)).psStream
                .setNumber(2).execute(oauth);
        marshaller.marshal(feed, System.out);
    }
}