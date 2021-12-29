package com.iamatum.iamatumgraphs.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The parameters that are received from DataTables
 */
@Data
@NoArgsConstructor
public class TablePage {

    private Integer draw;
    private Integer start;
    private Integer length;
    private List<Order> order;
    private List<Column> columns;

}

