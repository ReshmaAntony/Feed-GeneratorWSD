package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;

public class ISINGenerator {

        public static String generateISIN() {
            String countryCode = randomLetters(2);
            String middle = randomAlphaNumeric(9);
            String partial = countryCode + middle;
            int checkDigit = calculateCheckDigit(partial);
            return partial + checkDigit;
        }

        private static String randomLetters(int length) {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                char c = (char) ('A' + rand.nextInt(26));
                sb.append(c);
            }
            return sb.toString();
        }

        private static String randomAlphaNumeric(int length) {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append(chars.charAt(rand.nextInt(chars.length())));
            }
            return sb.toString();
        }

        private static int calculateCheckDigit(String isinWithoutCheckDigit) {
            StringBuilder numeric = new StringBuilder();
            for (char c : isinWithoutCheckDigit.toCharArray()) {
                if (Character.isLetter(c)) {
                    numeric.append(10 + (c - 'A'));
                } else {
                    numeric.append(c);
                }
            }

            String digits = numeric.toString();
            int sum = 0;
            boolean doubleIt = true;

            for (int i = digits.length() - 1; i >= 0; i--) {
                int digit = digits.charAt(i) - '0';
                if (doubleIt) {
                    digit *= 2;
                    if (digit > 9) digit = digit / 10 + digit % 10;
                }
                sum += digit;
                doubleIt = !doubleIt;
            }

            return (10 - (sum % 10)) % 10;
        }
    }
