package com.ihomziak.webbankingapp.util;

import java.util.Random;

public class AccountNumberGenerator {

    private static final int ACCOUNT_NUMBER_LENGTH = 12;

    public static String generateBankAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        // Generate a random number with the specified length
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            int digit = random.nextInt(10); // Generates a digit between 0 and 9
            accountNumber.append(digit);
        }

        return accountNumber.toString();
    }
}
