package com.iamatum.iamatumgraphs.converters;

import com.iamatum.iamatumgraphs.model.LcpsData;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConvertCsvToListTest {

    @Test
    void shouldConvertLcpsFileToListBean() throws FileNotFoundException {


        Path path = Paths.get("src", "test", "resources", "lcps.csv");

        assertTrue(Files.exists(path));

        File file = path.toFile();
        InputStream inputStream = new FileInputStream(file);

        List<LcpsData> lcpsData = new ConvertCsvToList<LcpsData>()
                .execute(inputStream, LcpsData.class);
        assertThat(lcpsData).isNotEmpty();
    }
}