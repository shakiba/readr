package x.readr.api0;

import org.testng.annotations.Test;

import x.readr.api0.req.read.StreamItem;
import x.readr.test.GenericRequestTest;
import x.readr.test.Sample;

public class StreamItemTest extends GenericRequestTest {

    @Test
    public void test() throws Exception {
        StreamItem req = new StreamItem(Sample.cnn_top, 100);
        req.psStream.setNewestTime(1317146551);
        // int t = (int) (System.currentTimeMillis() / 1000 - 12 * 30 * 24 *
        // 3600);
        // req.psStream.setNewestTime(t);
        Object execute = req.execute(simple);
        gson.toJson(execute, System.out);
    }
}