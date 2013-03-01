package me.shakiba.readr.model;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class StreamIdTest {

    public void test() throws Exception {
        Assert.assertTrue(StreamId.id("user/00000000000000000001/label/X")
                .equals(StreamId.id("user/-/label/X")));
        Assert.assertTrue(StreamId.id("user/-/label/X").equals(
                StreamId.id("user/00000000000000000001/label/X")));
        Assert.assertEquals(StreamId.label("X").forUser(UserId.fromLong(1L))
                .toString(), "user/00000000000000000001/label/X");
        Assert.assertEquals(StreamId.label("X").forUser(UserId.fromLong(-1L))
                .toString(), "user/00000000000000000001/label/X");

    }
}
