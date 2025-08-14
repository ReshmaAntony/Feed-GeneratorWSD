package com.solvians.showcase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.concurrent.*;


public class CertificateUpdateGenerator {
    private final int threads;
    private final int quotes;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
    }

    public Stream<String> generateQuotes() {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < threads * quotes; i++) {
            futures.add(executor.submit(new CertificateUpdateExecutor()));
        }

        List<String> results = new ArrayList<>();
        for (Future<String> f : futures) {
            try {
                results.add(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return results.stream();
    }

}
