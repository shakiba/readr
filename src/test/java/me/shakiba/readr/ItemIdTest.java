package me.shakiba.readr;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ItemIdTest {

    public void test() {
        String[] s = (Long.MAX_VALUE + " " + 1 + " " + 0 + " " + -1 + " " + Long.MIN_VALUE)
                .split("\\s");
        for (String s1 : s) {
            String l1 = ItemId.fromShort(s1).getLongForm();
            String s2 = ItemId.fromLong(l1).getShortForm();
            String l2 = ItemId.fromShort(s2).getLongForm();
            System.out.println(s1 + (!s1.equals(s2) ? " != " + s2 : ""));
            System.out.println(l1 + (!l1.equals(l2) ? " != " + l2 : ""));
            Assert.assertEquals(s1, s2);
            Assert.assertEquals(l1, l2);
            Assert.assertEquals(s1, ItemId.fromAny(s1).getShortForm());
            Assert.assertEquals(l1, ItemId.fromAny(l1).getLongForm());
        }
    }
}