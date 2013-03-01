package me.shakiba.readr.api0;

import me.shakiba.readr.GenericRequestTest;
import me.shakiba.readr.Sample;
import me.shakiba.readr.api0.model.Api0Stream;
import me.shakiba.readr.api0.req.read.ItemContent;

//@Test
public class ItemContentTest extends GenericRequestTest {

    public void test() throws Exception {
        Api0Stream execute = new ItemContent(true, Sample.item1, Sample.item2)
                .execute(simple);
        gson.toJson(execute, System.out);
    }
}