package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CertificateUpdateExecutorTest {

    @Test
    void generateValidCertificateUpdate() throws Exception {
        CertificateUpdateExecutor executor = new CertificateUpdateExecutor();

        String result = executor.call();

        assertNotNull(result, "Certificate  should not be null");
        assertFalse(result.isEmpty(), "Certificate should not be empty");

        String[] parts = result.split(",");
        assertEquals(6, parts.length, "There should be exactly 6 comma-separated values");
        assertDoesNotThrow(() -> Long.parseLong(parts[0]), "Timestamp should be a number");
        assertEquals(12, parts[1].length(), "ISIN should have length 12");
        assertDoesNotThrow(() -> Double.parseDouble(parts[2]), "Bid price should be a valid decimal");
        assertDoesNotThrow(() -> Integer.parseInt(parts[3]), "Bid size should be a valid integer");
        assertDoesNotThrow(() -> Double.parseDouble(parts[4]), "Ask price should be a valid decimal");
        assertDoesNotThrow(() -> Integer.parseInt(parts[5]), "Ask size should be a valid integer");
    }
}
