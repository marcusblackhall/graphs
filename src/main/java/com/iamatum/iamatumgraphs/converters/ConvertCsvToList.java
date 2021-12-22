package com.iamatum.iamatumgraphs.converters;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class ConvertCsvToList<T> {

    public List<T> execute(InputStream inputStream, Class<T> clazz) {

        Reader fileReader = new InputStreamReader(inputStream);
        return new CsvToBeanBuilder(fileReader)
                .withType(clazz)
                .build()
                .parse();

    }
}
