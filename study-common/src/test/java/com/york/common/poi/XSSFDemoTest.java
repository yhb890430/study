package com.york.common.poi;

import org.junit.Test;

import static org.junit.Assert.*;

public class XSSFDemoTest {

    @Test
    public void readDemo() {
        XSSFDemo demo = new XSSFDemo();
        demo.readDemo();
    }

    @Test
    public void readLargeDemo(){
        XSSFDemo demo = new XSSFDemo();
        demo.readLargeExcel();
    }

    @Test
    public void exportDemo(){
        XSSFDemo demo = new XSSFDemo();
        demo.exportDemo();
    }
}