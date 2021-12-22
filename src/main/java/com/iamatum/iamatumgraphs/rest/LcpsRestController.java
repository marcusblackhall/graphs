package com.iamatum.iamatumgraphs.rest;

import com.iamatum.iamatumgraphs.model.LcpsData;
import com.iamatum.iamatumgraphs.services.LcpsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LcpsRestController {

    private final LcpsService lcpsService;

    @Operation(description = "Retrieves LCPS corona data between two dates")
    @GetMapping("/lcps/betweenDates")
    public List<LcpsData> dataBetweenDates(@Parameter( description = "format yyyy-mm-dd") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                           @Parameter( description = "format yyyy-mm-dd")@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return lcpsService.lcpsBetweenDates(startDate, endDate);

    }

}
