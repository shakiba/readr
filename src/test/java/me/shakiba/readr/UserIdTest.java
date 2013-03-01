package me.shakiba.readr;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UserIdTest {

    public void test() {
        Long[] signeds = { Long.MAX_VALUE, 1L, 0L, -1L, Long.MIN_VALUE };
        for (Long s1 : signeds) {
            // System.out.println(s1);
            String u1 = UserId.fromLong(s1).getUnsigned();
            // System.out.println(u1);
            Long s2 = UserId.fromUnsigned(u1).getValue();
            // System.out.println(s2);
            String u2 = UserId.fromLong(s2).getUnsigned();
            // System.out.println(u2);
            System.out.print(u1 + (!u1.equals(u2) ? " != " + u2 : "") + "\t");
            System.out.println(s1 + (!s1.equals(s2) ? " != " + s2 : ""));
            // System.out.println();
            Assert.assertEquals(s1, s2);
            Assert.assertEquals(u1, u2);
        }

        compare((-1L));
        compare((-4611686018427387904L));
        compare((-8446744073709551615L));
        compare((-8446744073709551616L));
        compare((-8446744073709551617L));
        compare((-9223372036854775808L));
        compare((9223372036854775807L));
        compare((1000000000000000001L));
        compare((1000000000000000000L));
        compare((999999999999999999L));
        compare((1L));
        compare((0L));
    }

    private static void compare(long x) {
        String uns = UserId.longToUnsigned(x);
        System.out.println(String.format("%020d", x) + "\t" + uns + "\t"
                + (UserId.unsignedToLong(uns) == x));
    }
}