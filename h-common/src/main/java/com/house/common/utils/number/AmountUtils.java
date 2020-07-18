package com.house.common.utils.number;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description
 * @Author huangW
 * @Date 9:52 2020/7/18
 * @Version 1.0
 */
public class AmountUtils {

    private AmountUtils() {
    }

    public static double add(double oneAmount, double twoAmount) {
        return BigDecimal.valueOf(oneAmount).add(BigDecimal.valueOf(twoAmount)).doubleValue();
    }

    public static double subtract(double oneAmount, double twoAmount) {
        return BigDecimal.valueOf(oneAmount).subtract(BigDecimal.valueOf(twoAmount)).doubleValue();
    }

    public static double multiply(double oneAmount, double twoAmount) {
        return BigDecimal.valueOf(oneAmount).multiply(BigDecimal.valueOf(twoAmount)).doubleValue();
    }

    public static double divide(double oneAmount, double twoAmount) {
        return divide(oneAmount, twoAmount, 2);
    }

    public static double divide(double oneAmount, double twoAmount, int scale) {
        return divide(oneAmount, twoAmount, scale, RoundingMode.HALF_UP);
    }

    public static double divide(double oneAmount, double twoAmount, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            return BigDecimal.valueOf(oneAmount).divide(BigDecimal.valueOf(twoAmount), scale, roundingMode).doubleValue();
        }
    }

    public static double round(double amount, int scale) {
        return round(amount, scale, RoundingMode.HALF_UP);
    }

    public static double round(double amount, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            return BigDecimal.valueOf(amount).setScale(scale, roundingMode).doubleValue();
        }
    }

    public static boolean eq(double onwAmount, double twoAmount) {
        return BigDecimal.valueOf(onwAmount).compareTo(BigDecimal.valueOf(twoAmount)) == 0;
    }

    public static boolean less(double onwAmount, double twoAmount) {
        return BigDecimal.valueOf(onwAmount).compareTo(BigDecimal.valueOf(twoAmount)) < 0;
    }

    public static boolean greater(double onwAmount, double twoAmount) {
        return BigDecimal.valueOf(onwAmount).compareTo(BigDecimal.valueOf(twoAmount)) > 0;
    }

    public static boolean leq(double onwAmount, double twoAmount) {
        return BigDecimal.valueOf(onwAmount).compareTo(BigDecimal.valueOf(twoAmount)) <= 0;
    }

    public static boolean geq(double onwAmount, double twoAmount) {
        return BigDecimal.valueOf(onwAmount).compareTo(BigDecimal.valueOf(twoAmount)) >= 0;
    }

    public static double negate(double amount) {
        return BigDecimal.valueOf(amount).negate().doubleValue();
    }
}

