package com.iamatum.iamatumgraphs.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
public class Lcps {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate datum;

    private Integer icBedsNl;

    private Integer icBedsInt;

    private Integer icBedsNonCovid;

    private Integer kliniekBedsNl;

    private Integer icBedsNieuwNl;

    private Integer kliniekNieuwNl;

}
