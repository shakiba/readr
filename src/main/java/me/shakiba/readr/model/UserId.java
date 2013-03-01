package me.shakiba.readr.model;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * User IDs are 64-bit numbers and are represented in API0 inputs and outputs in
 * form of unsigned long.
 */
public class UserId {
    private Long id;

    private UserId(Long value) {
        this.id = value;
    }

    public static UserId fromUnsigned(String unsigned) {
        return new UserId(unsignedToLong(unsigned));
    }

    public static UserId fromLong(Long value) {
        return new UserId(value);
    }

    public static UserId fromLong(String longValue) {
        return new UserId(Long.valueOf(longValue));
    }

    public String getUnsigned() {
        return longToUnsigned(id);
    }

    public String getSigned() {
        return String.valueOf(id);
    }

    public Long getValue() {
        return id;
    }

    @Override
    public String toString() {
        return getUnsigned();
    }

    public String debug() {
        return getUnsigned() + "=" + id;
    }

    static long unsignedToLong(String id) {
        return new BigInteger(id).longValue();
    }

    private static DecimalFormat zero19 = new DecimalFormat(
            "0000000000000000000");

    static String longToUnsigned(long x) {
        if (x >= 1000000000000000000L) {
            return "0" + x;
        } else if (x >= 0) {
            return "0" + zero19.format(x);
        } else if (x >= -7446744073709551616L) {
            return "1" + (x + 8446744073709551616L);
        } else if (x >= -8446744073709551616L) {
            return "1" + zero19.format(x + 8446744073709551616L);
        } else {
            return "09" + (x - 9000000000000000000L);
        }
    }
}