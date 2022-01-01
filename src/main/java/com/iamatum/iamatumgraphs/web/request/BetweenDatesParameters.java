package com.iamatum.iamatumgraphs.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetweenDatesParameters {

    @NotEmpty(message = "Start Date must be entered")
    private String startDate;
    @NotEmpty(message = "End Date must be entered")
    private String enddate;
}
