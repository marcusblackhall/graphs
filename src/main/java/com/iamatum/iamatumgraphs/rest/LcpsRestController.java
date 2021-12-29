package com.iamatum.iamatumgraphs.rest;

import com.iamatum.iamatumgraphs.model.LcpsData;
import com.iamatum.iamatumgraphs.model.ResultPage;
import com.iamatum.iamatumgraphs.model.TablePage;
import com.iamatum.iamatumgraphs.services.LcpsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LcpsRestController {

    private final LcpsService lcpsService;

    @Operation(description = "Retrieves LCPS corona data between two dates")
    @GetMapping("/lcps/betweenDates")
    public ResponseEntity<List<LcpsData>> dataBetweenDates(
            @Valid
            @NotNull(message = "Start date cannot be empty")
            @Parameter(description = "format yyyy-MM-dd")
            @RequestParam
            @Pattern(regexp = ("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"), message = "Format is yyyy-mm-dd") String startDate,
            @Parameter(description = "format yyyy-MM-dd")
            @RequestParam
            @Pattern(regexp = ("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"), message = "Format is yyyy-mm-dd") String endDate) {

        return ResponseEntity.ok(lcpsService.lcpsBetweenDates(LocalDate.parse(startDate), LocalDate.parse(endDate)));

    }

    @PostMapping( "/lcps/datatables")
    public ResponseEntity<ResultPage> createLcpsDatatableData(@RequestBody TablePage tablePage){
        ResultPage resultPage = lcpsService.lcpsDataTables(tablePage);
        return  ResponseEntity.ok(resultPage);
    }

}
