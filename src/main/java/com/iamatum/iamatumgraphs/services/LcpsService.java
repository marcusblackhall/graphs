package com.iamatum.iamatumgraphs.services;


import com.iamatum.iamatumgraphs.domain.Lcps;
import com.iamatum.iamatumgraphs.mappers.LcpsMapper;
import com.iamatum.iamatumgraphs.model.*;
import com.iamatum.iamatumgraphs.repositories.LcpsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LcpsService {

    private final LcpsRepository lcpsRepository;
    private final LcpsMapper lcpsMapper;

    public List<LcpsData> lcpsBetweenDates(LocalDate startDate, LocalDate endDate) {

        List<Lcps> range = lcpsRepository.findByDatumBetweenOrderByDatum(startDate, endDate);

        return range.stream()
                .map(lcpsMapper::lcpsToLcpsData)
                .collect(Collectors.toList());

    }

    public int loadLcpsData(List<LcpsData> lcpsData) {
        lcpsRepository.deleteAll();
        List<Lcps> lcpsList = lcpsData.stream().map(lcpsMapper::lcpsDataToLcps).collect(Collectors.toList());
        lcpsRepository.saveAll(lcpsList);
        return lcpsList.size();
    }

    public ResultPage lcpsDataTables(TablePage tablePage) {

        Integer pageSize = tablePage.getLength();
        Sort orders = translatePageOrder(tablePage);

        var of = PageRequest.of(calculatePage(tablePage), pageSize, orders);
        Page<Lcps> all = lcpsRepository.findAll(of);

        var resultPage = new ResultPage();
        resultPage.setData(all.getContent());

        resultPage.setDraw(tablePage.getDraw());
        resultPage.setRecordsTotal((int) all.getTotalElements());
        resultPage.setRecordsFiltered((int) all.getTotalElements());
        return resultPage;


    }

    private Sort translatePageOrder(TablePage tablePage) {

        List<Order> ordering = tablePage.getOrder();
        var order = ordering.get(0);
        Integer column = order.getColumn();
        List<Column> columns = tablePage.getColumns();
        String name = columns.get(column).getData();

        if (order.getDir().equals("asc")) {
            return Sort.by(Sort.Order.asc(name));
        }
        return Sort.by(Sort.Order.desc(name));


    }

    private int calculatePage(TablePage tablePage) {

        Integer start = tablePage.getStart();
        Integer length = tablePage.getLength();

        return start / length;


    }

}

