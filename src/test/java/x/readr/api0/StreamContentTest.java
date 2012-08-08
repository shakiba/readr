package x.readr.api0;

import org.testng.annotations.Test;

import x.readr.api0.model.Api0Item;
import x.readr.api0.model.Api0Stream;
import x.readr.api0.req.read.StreamContent;
import x.readr.test.GenericRequestTest;
import x.readr.test.Sample;

@Test
public class StreamContentTest extends GenericRequestTest {

    @Test
    public void test() throws Exception {
        Api0Stream execute = new StreamContent(Sample.cnn_top).psStream
                .setNumber(2).execute(simple);
        for (Api0Item item : execute.items) {
            System.out.println(item.id);
        }
        gson.toJson(execute, System.out);
    }
}