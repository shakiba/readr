package x.readr.api0;

import x.readr.api0.model.Api0Stream;
import x.readr.api0.req.read.ItemContent;
import x.readr.test.GenericRequestTest;
import x.readr.test.Sample;

//@Test
public class ItemContentTest extends GenericRequestTest {

    public void test() throws Exception {
        Api0Stream execute = new ItemContent(true, Sample._99, Sample._98)
                .execute(simple);
        gson.toJson(execute, System.out);
    }
}