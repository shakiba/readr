package me.shakiba.readr.api0;

import me.shakiba.readr.api0.req.read.StreamItem;
import me.shakiba.readr.test.GenericRequestTest;
import me.shakiba.readr.test.Sample;

//@Test
public class StreamItemTest extends GenericRequestTest {

    public void test() throws Exception {
        StreamItem req = new StreamItem(Sample.feed, 100);
        req.psStream.setNewestTime(1317146551);
        // int t = (int) (System.currentTimeMillis() / 1000 - 12 * 30 * 24 *
        // 3600);
        // req.psStream.setNewestTime(t);
        Object execute = req.execute(simple);
        gson.toJson(execute, System.out);
    }
}