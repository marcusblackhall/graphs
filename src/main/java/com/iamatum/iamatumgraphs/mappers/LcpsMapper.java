package com.iamatum.iamatumgraphs.mappers;

import com.iamatum.iamatumgraphs.domain.Lcps;
import com.iamatum.iamatumgraphs.model.LcpsData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LcpsMapper {

    LcpsData lcpsToLcpsData(Lcps lcps);

    Lcps lcpsDataToLcps(LcpsData lcpsData);
}

