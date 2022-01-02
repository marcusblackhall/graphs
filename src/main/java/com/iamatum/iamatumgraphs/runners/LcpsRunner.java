package com.iamatum.iamatumgraphs.runners;

import com.iamatum.iamatumgraphs.repositories.LcpsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LcpsRunner implements CommandLineRunner {

    private LcpsRepository lcpsRepository;

    public LcpsRunner(LcpsRepository lcpsRepository) {
        this.lcpsRepository = lcpsRepository;
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
