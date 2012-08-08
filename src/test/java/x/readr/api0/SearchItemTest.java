package x.readr.api0;

import org.testng.annotations.Test;

import x.readr.api0.req.read.SearchItemIds;
import x.readr.test.GenericRequestTest;
import x.readr.test.Sample;

@Test
public class SearchItemTest extends GenericRequestTest {

    @Test
    public void test() throws Exception {
        String keywords = "Iran";
        Object execute = new SearchItemIds(keywords, Sample.cnn_top).execute(oauth);
        gson.toJson(execute, System.out);
    }
}