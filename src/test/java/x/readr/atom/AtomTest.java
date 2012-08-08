package x.readr.atom;

import org.testng.annotations.Test;

import x.readr.StreamId;
import x.readr.atom.model.Feed;
import x.readr.atom.req.AtomStream;
import x.readr.test.GenericRequestTest;


@Test
public class AtomTest extends GenericRequestTest {

    @Test
    public void test() throws Exception {
        Feed feed = new AtomStream(
                StreamId.feed("http://wvs.topleftpixel.com/index.rdf")).psStream
                .setNumber(2).execute(oauth);
        marshaller.marshal(feed, System.out);
    }
}