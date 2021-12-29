package com.iamatum.iamatumgraphs.repositories;

import com.iamatum.iamatumgraphs.domain.Lcps;
import com.iamatum.iamatumgraphs.model.LcpsData;
import com.iamatum.iamatumgraphs.services.LcpsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LcpsRepositoryTest {

    @Autowired
    private LcpsRepository lcpsRepository;

    @Autowired
    private LcpsService lcpsService;

    @BeforeEach
    void setUp() {

        lcpsRepository.deleteAll();

        Lcps lcps1 = Lcps.builder()
                .datum(LocalDate.parse("2021-12-01"))
                .icBedsInt(5)
                .build();

        Lcps lcps2 = Lcps.builder()
                .datum(LocalDate.parse("2021-12-02"))
                .icBedsInt(6)
                .build();
        Lcps lcps3 = Lcps.builder()
                .datum(LocalDate.parse("2021-12-03"))
                .icBedsInt(7)
                .build();


        lcpsRepository.saveAll(List.of(lcps1, lcps2, lcps3));
        assertThat(lcpsRepository.findAll().size()).isEqualTo(3);


    }

    @Test
    void findByDatumStartingWithEndingWith() {
        LocalDate startDate = LocalDate.parse("2021-12-01");
        LocalDate endDate = LocalDate.parse("2021-12-03");
        List<LcpsData> lcpsList = lcpsService.lcpsBetweenDates(startDate, endDate);
        assertThat(lcpsList.size()).isEqualTo(3);
    }

    @Test
    void pagingTests(){

        PageRequest pageRequest = PageRequest.of(0,
                10,
                Sort.by(Sort.Order.desc("icBedsInt"))
                );


        Page<Lcps> pageLcps = lcpsRepository.findAll(pageRequest);
        assertThat(pageLcps.getTotalElements()).isEqualTo(3);
        Optional<Lcps> first = pageLcps.get().findFirst();
        assertThat(first.get().getIcBedsInt()).isEqualTo(7);


    }

    @AfterEach
    void tearDown() {
    }
}