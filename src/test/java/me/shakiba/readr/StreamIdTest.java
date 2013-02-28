package me.shakiba.readr;

import me.shakiba.readr.StreamId;
import me.shakiba.readr.UserId;

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
