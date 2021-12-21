package com.iamatum.iamatumgraphs.repositories;

import com.iamatum.iamatumgraphs.domain.Lcps;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LcpsRepository extends JpaRepository<Lcps,Integer> {

    List<Lcps> findByDatumBetweenOrderByDatum(LocalDate startDate ,LocalDate endDate);
}
