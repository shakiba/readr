package x.readr.api0;

import org.testng.annotations.Test;

import x.readr.test.GenericRequestTest;

@Test
public class MeTest extends GenericRequestTest {

    public void test() throws Exception {
        System.out.println(me());
    }
}