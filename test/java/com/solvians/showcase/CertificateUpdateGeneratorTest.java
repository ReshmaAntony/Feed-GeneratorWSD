package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

class CertificateUpdateGeneratorTest {

    @Test
    public void generateQuotes() {
        int threads = 10;
        int quotes = 100;
        CertificateUpdateGenerator generator = new CertificateUpdateGenerator(threads, quotes);

        Stream<String> quoteStream = generator.generateQuotes();

        assertNotNull(quoteStream, "Quotes should not be null");

        long count = quoteStream.peek(line -> {
            assertNotNull(line, "Quote line should not be null");
            assertFalse(line.isBlank(), "Quote line should not be blank");
        }).count();

        assertEquals(threads * quotes, count, "Number of generated quotes should match threads × quotes");
    }

    @Test
    void passZeroQuotes() {
        CertificateUpdateGenerator generator = new CertificateUpdateGenerator(5, 0);
        List<String> results = generator.generateQuotes().collect(Collectors.toList());
        assertTrue(results.isEmpty(), "With zero quotes, the result should be empty");
    }

    }