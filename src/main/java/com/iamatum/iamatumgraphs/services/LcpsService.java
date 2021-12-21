package com.iamatum.iamatumgraphs.services;


import com.iamatum.iamatumgraphs.domain.Lcps;
import com.iamatum.iamatumgraphs.mappers.LcpsMapper;
import com.iamatum.iamatumgraphs.model.LcpsData;
import com.iamatum.iamatumgraphs.repositories.LcpsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LcpsService {

    private final LcpsRepository lcpsRepository;
    private final LcpsMapper lcpsMapper;

    public List<LcpsData> lcpsBetweenDates(LocalDate startDate, LocalDate endDate) {

        List<Lcps> range = lcpsRepository.findByDatumBetweenOrderByDatum(startDate, endDate);

        return range.stream()
                .map(lcpsMapper::lcpsToLcpsData)
                .collect(Collectors.toList());

    }

}

