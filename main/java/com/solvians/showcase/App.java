package com.solvians.showcase;

public class App {

    private final int threads;
    private final int quotes;

    public App(String threads, String quotes) {

        this.threads = Integer.parseInt(threads);
        this.quotes = Integer.parseInt(quotes);
    }

    public void run() {
        CertificateUpdateGenerator generator = new CertificateUpdateGenerator(threads, quotes);
        generator.generateQuotes().forEach(System.out::println);
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            new App(args[0], args[1]).run();
        }
        else {
            throw new RuntimeException("Expecting number of threads and quotes. But got this: " + args.length);
        }
    }
}
