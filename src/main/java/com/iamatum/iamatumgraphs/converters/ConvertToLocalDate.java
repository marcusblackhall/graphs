package com.iamatum.iamatumgraphs.converters;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertToLocalDate extends AbstractBeanField {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
    private static final DateTimeFormatter dMFormatter = DateTimeFormatter.ofPattern("d-M-yyyy");

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (s != null) {
            if (s.matches("^.........$")){
                return LocalDate.parse(s, monthFormatter);
            }
            if (s.matches("^........$")){
                return LocalDate.parse(s, dMFormatter);
            }
            return LocalDate.parse(s, formatter);
        }
        return null;
    }
}
