package me.shakiba.readr.api0;

import me.shakiba.readr.test.GenericRequestTest;

import org.testng.annotations.Test;

@Test
public class MeTest extends GenericRequestTest {

    public void test() throws Exception {
        System.out.println(me());
    }
}