package com.hashkart.assignment.purchasedItem.util;

import java.util.Arrays;

public class CommonUtil {

    public static final String COUPON_CODE = "Axubz";

    public static boolean isValidString(String... s){
        return s != null && Arrays.stream(s).allMatch(s1 ->  s1 != null && !s1.isEmpty());
    }
}
