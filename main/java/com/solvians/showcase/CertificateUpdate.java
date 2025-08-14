package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;

public class CertificateUpdate {

    // TODO: implement me.

    private final long timestamp;
    private final String isin;
    private final double bidPrice;
    private final int bidSize;
    private final double askPrice;
    private final int askSize;

    public CertificateUpdate(long timestamp, String isin, double bidPrice, int bidSize, double askPrice, int askSize) {
        this.timestamp = timestamp;
        this.isin = isin;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.askPrice = askPrice;
        this.askSize = askSize;
    }

    public static CertificateUpdate randomUpdate() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new CertificateUpdate(
                System.currentTimeMillis(),
                ISINGenerator.generateISIN(),
                round(rand.nextDouble(100.00, 200.01), 2),
                rand.nextInt(1000, 5001),
                round(rand.nextDouble(100.00, 200.01), 2),
                rand.nextInt(1000, 10001)
        );
    }

    private static double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public String toString() {
        return String.format("%d,%s,%.2f,%d,%.2f,%d",
                             timestamp, isin, bidPrice, bidSize, askPrice, askSize);
    }
}
