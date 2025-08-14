package com.solvians.showcase;

import java.util.concurrent.Callable;

public class CertificateUpdateExecutor implements Callable<String> {
    @Override
    public String call() throws Exception {
        return CertificateUpdate.randomUpdate().toString();
    }

}


