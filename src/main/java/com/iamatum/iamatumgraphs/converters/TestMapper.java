package com.iamatum.iamatumgraphs.converters;

import com.iamatum.iamatumgraphs.domain.Lcps;
import com.iamatum.iamatumgraphs.model.LcpsData;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {

    public static Lcps lcpsDataToLcps(LcpsData lcpsData) {

        if (lcpsData == null) {
            return null;
        }

        return Lcps.builder()
                .datum(lcpsData.getDatum())
                .icBedsInt(lcpsData.getIcBedsInt())
                .icBedsNl(lcpsData.getIcBedsNl())
                .kliniekBedsNl(lcpsData.getKliniekBedsNl())
                .icBedsNonCovid(lcpsData.getIcBedsNonCovid())
                .icBedsNieuwNl(lcpsData.getIcBedsNieuwNl())
                .kliniekNieuwNl(lcpsData.getKliniekNieuwNl())
                .build();


    }
}
