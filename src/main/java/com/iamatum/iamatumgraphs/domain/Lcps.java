package com.iamatum.iamatumgraphs.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Table(indexes = @Index(columnList = "datum"))
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
