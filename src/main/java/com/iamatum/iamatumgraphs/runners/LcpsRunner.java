package com.iamatum.iamatumgraphs.runners;

import com.iamatum.iamatumgraphs.converters.ConvertCsvToList;
import com.iamatum.iamatumgraphs.converters.TestMapper;
import com.iamatum.iamatumgraphs.domain.Lcps;
import com.iamatum.iamatumgraphs.model.LcpsData;
import com.iamatum.iamatumgraphs.repositories.LcpsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LcpsRunner implements CommandLineRunner {

    private LcpsRepository lcpsRepository;
    public LcpsRunner(LcpsRepository lcpsRepository){
        this.lcpsRepository = lcpsRepository;
    }

    @Override
    public void run(String... args) throws Exception {



    }
}
