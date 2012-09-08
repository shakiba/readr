package x.readr.api0;

import org.testng.Assert;

import x.readr.api0.model.Api0FeedFind;
import x.readr.api0.req.read.FeedFinder;
import x.readr.test.GenericRequestTest;

//@Test
public class FeedFinderTest extends GenericRequestTest {

    public void test() throws Exception {
        Api0FeedFind execute = new FeedFinder("http://www.cnn.com/")
                .execute(simple);

        gson.toJson(execute, System.out);

        Assert.assertEquals(execute.feed.get(0).href,
                "http://rss.cnn.com/rss/cnn_topstories.rss");
    }
}