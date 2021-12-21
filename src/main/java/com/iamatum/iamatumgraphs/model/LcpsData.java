package com.iamatum.iamatumgraphs.model;

import com.iamatum.iamatumgraphs.converters.ConvertToInteger;
import com.iamatum.iamatumgraphs.converters.ConvertToLocalDate;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LcpsData {

    @CsvCustomBindByName(column = "Datum", converter = ConvertToLocalDate.class)
    private LocalDate datum;

    @CsvCustomBindByName(column = "IC_Bedden_COVID_Nederland", converter = ConvertToInteger.class)
    private Integer icBedsNl;

    @CsvCustomBindByName(column = "IC_Bedden_COVID_Internationaal", converter = ConvertToInteger.class)
    private Integer icBedsInt;

    @CsvCustomBindByName(column = "IC_Bedden_Non_COVID_Nederland", converter = ConvertToInteger.class)
    private Integer icBedsNonCovid;

    @CsvCustomBindByName(column = "Kliniek_Bedden_Nederland", converter = ConvertToInteger.class)
    private Integer kliniekBedsNl;

    @CsvCustomBindByName(column = "IC_Nieuwe_Opnames_COVID_Nederland", converter = ConvertToInteger.class)
    private Integer icBedsNieuwNl;

    @CsvCustomBindByName(column = "Kliniek_Nieuwe_Opnames_COVID_Nederland", converter = ConvertToInteger.class)
    private Integer kliniekNieuwNl;


}
