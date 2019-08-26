package com.york.common.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @description: Apache POI Excel demo
 * @author: york
 * @date: 2019-8-26 10:46
 * @version: <1.0>
 */
public class ExcelDemo {

    public void readExcel(){
        new HSSFWorkbook();
        new XSSFWorkbook();
    }

    public void writeExcel(){

    }
}
