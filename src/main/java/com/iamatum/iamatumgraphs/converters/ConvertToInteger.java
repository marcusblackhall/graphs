package com.iamatum.iamatumgraphs.converters;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class ConvertToInteger extends AbstractBeanField {
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (s != null && !s.isBlank() && !s.trim().equals("NA")) {
            return Integer.parseInt(s);
        }
        return 0;
    }
}
