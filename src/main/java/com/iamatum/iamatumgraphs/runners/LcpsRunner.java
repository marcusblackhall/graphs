package com.iamatum.iamatumgraphs.runners;

import com.iamatum.iamatumgraphs.converters.ConvertCsvToList;
import com.iamatum.iamatumgraphs.converters.Mapper;
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


        lcpsRepository.deleteAll();

        Path path = Paths.get("src", "test", "resources", "lcps.csv");


        File file = path.toFile();
        InputStream inputStream = new FileInputStream(file);

        List<LcpsData> lcpsData = new ConvertCsvToList<LcpsData>()
                .execute(inputStream, LcpsData.class);

        List<Lcps> allLcps = lcpsData.stream()
                .map(Mapper::lcpsDataToLcps)
                .collect(Collectors.toList());

        lcpsRepository.saveAll(allLcps);


    }
}
