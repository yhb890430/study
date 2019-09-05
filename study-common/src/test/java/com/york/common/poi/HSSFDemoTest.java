package com.york.common.poi;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HSSFDemoTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void hssfReadDemo() {
        HSSFDemo hssfDemo = new HSSFDemo();
        hssfDemo.readDemo();
    }

    @Test
    public void writeExcel() {
        HSSFDemo hssfDemo = new HSSFDemo();
        hssfDemo.writeDemo();
    }
}