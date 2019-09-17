package com.york.common.poi;

import org.junit.Test;

import static org.junit.Assert.*;

public class SXSSFDemoTest {

    @Test
    public void exportLargeExcelDemo() {
        try{
            long begin = System.currentTimeMillis();
            SXSSFDemo demo = new SXSSFDemo();
            demo.exportLargeExcelDemo();
            long end = System.currentTimeMillis();
            System.out.println("所花时间:" + (end - begin));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void exportEncryptedLargeExcelDemo(){
        try{
            long begin = System.currentTimeMillis();
            SXSSFDemo demo = new SXSSFDemo();
            demo.exportEncryptedLargeExcelDemo();
            long end = System.currentTimeMillis();
            System.out.println("所花时间:" + (end - begin));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}