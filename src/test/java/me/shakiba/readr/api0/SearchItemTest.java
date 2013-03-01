package me.shakiba.readr.api0;

import me.shakiba.readr.GenericRequestTest;
import me.shakiba.readr.Sample;
import me.shakiba.readr.api0.req.read.SearchItemIds;

//@Test
public class SearchItemTest extends GenericRequestTest {

    public void test() throws Exception {
        String keywords = "Iran";
        Object execute = new SearchItemIds(keywords, Sample.feed)
                .execute(oauth);
        gson.toJson(execute, System.out);
    }
}