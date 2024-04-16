package com.bank.customer.onboarding.util;


import java.util.Random;


public class IbanGenerationUtil {


    public static String createRandomIban(String countryCode) {
        return countryCode + generateRandom2DigitInt() + "ABNA" + generateRandom14DigitLong();
    }
    private static String generateRandom14DigitLong() {
        long min = 10000000000000L;
        long max = 99999999999999L;
        Random random = new Random();
        return String.valueOf(min + ((long) (random.nextDouble() * (max - min))));
    }

    private static String generateRandom2DigitInt() {
        Random random = new Random();
        int randomInt = random.nextInt(90) + 10;
        return String.valueOf(randomInt);
    }


}
