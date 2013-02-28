package me.shakiba.readr.api0;

import me.shakiba.readr.api0.model.Api0Item;
import me.shakiba.readr.api0.model.Api0Stream;
import me.shakiba.readr.api0.req.read.StreamContent;
import me.shakiba.readr.test.GenericRequestTest;
import me.shakiba.readr.test.Sample;

//@Test
public class StreamContentTest extends GenericRequestTest {

    public void test() throws Exception {
        Api0Stream execute = new StreamContent(Sample.cnn_top).psStream
                .setNumber(2).execute(simple);
        for (Api0Item item : execute.items) {
            System.out.println(item.id);
        }
        gson.toJson(execute, System.out);
    }
}