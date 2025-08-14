package com.solvians.showcase;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.RepeatedTest;

import java.lang.reflect.Method;

public class ISINGeneratorTest {

    @Test
    void generateISINNumber() {
        String isin = ISINGenerator.generateISIN();

        assertNotNull(isin, "ISIN should not be null");
        assertEquals(12, isin.length(), "ISIN should be of 12 characters");
        assertTrue(isin.substring(0, 2).matches("[A-Z]{2}"),
                   "First two characters should be uppercase letters");
        assertTrue(isin.substring(2, 11).matches("[A-Z0-9]{9}"),
                   "Characters 3-11 should be alphanumeric");
        assertTrue(Character.isDigit(isin.charAt(11)),
                   "Last character should be a digit");
        String withoutCheckDigit = isin.substring(0, 11);
        int expectedCheckDigit = invokeCalculateCheckDigit(withoutCheckDigit);
        int actualCheckDigit = Character.getNumericValue(isin.charAt(11));
        assertEquals(expectedCheckDigit, actualCheckDigit,
                     "Check digit should be correct");
    }

    @RepeatedTest(5)
    void generateDifferentValuesISIN() {
        String isin1 = ISINGenerator.generateISIN();
        String isin2 = ISINGenerator.generateISIN();
        assertNotEquals(isin1, isin2, "ISIN generator should generate different values");
    }

    @Test
    void passEmptyStringToCalculateCheckDigit() throws Exception {
        Method method = ISINGenerator.class.getDeclaredMethod("calculateCheckDigit", String.class);
        method.setAccessible(true);

        int result = (int) method.invoke(null, "");
        assertEquals(0, result, "Pass Empty string should generate check digit as 0");
    }

    @Test
    void passInvalidCharactersToCalculateCheckDigit() throws Exception {
        Method method = ISINGenerator.class.getDeclaredMethod("calculateCheckDigit", String.class);
        method.setAccessible(true);

        String invalidISIN = "DE1234$6789";
        int result = (int) method.invoke(null, invalidISIN);
        assertTrue(result >= 0 && result <= 9, "Even with invalid character, check digit should be between 0-9");
    }

    private int invokeCalculateCheckDigit(String partialISIN) {
        try {
            var method = ISINGenerator.class.getDeclaredMethod("calculateCheckDigit", String.class);
            method.setAccessible(true);
            return (int) method.invoke(null, partialISIN);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke calculateCheckDigit", e);
        }
    }
}
