package me.shakiba.readr.api0;

import me.shakiba.readr.api0.req.read.SearchItemIds;
import me.shakiba.readr.test.GenericRequestTest;
import me.shakiba.readr.test.Sample;

//@Test
public class SearchItemTest extends GenericRequestTest {

    public void test() throws Exception {
        String keywords = "Iran";
        Object execute = new SearchItemIds(keywords, Sample.cnn_top)
                .execute(oauth);
        gson.toJson(execute, System.out);
    }
}