package com.york.common.utils;

import com.york.common.poi.util.TempFileUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @author: york
 * @date: 2019-12-12 14:33
 * @version: <1.0>
 */
public class Test {
    public static void main(String[] args) {

        Calendar begin = Calendar.getInstance();
        begin.setTimeInMillis(1496275200000L);
        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(1514764800000L);
        Map<String,Double> map = new HashMap<>();
        while (begin.before(end)){
            double v = randomDouble(1.0, -0.2);
            BigDecimal b = new BigDecimal(v);
            double rmoney = b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put(formateDate(begin.getTime()),rmoney);
            begin.add(Calendar.DAY_OF_YEAR,1);
        }
        XSSFWorkbook workbook = null;
        try {
            TempFileUtils.checkTempFiles();
            workbook = XSSFWorkbookFactory.createWorkbook();
            XSSFSheet sheet = workbook.createSheet("student");
            XSSFRow titleRow = sheet.createRow(0);
            titleRow.createCell(0).setCellValue("时间");
            titleRow.createCell(1).setCellValue("值");
            int row = 1;
            ArrayList<String> strings = new ArrayList<>(map.keySet());
            Collections.sort(strings);
            for(String key : strings){
                XSSFRow row1 = sheet.createRow(row);
                row1.createCell(0).setCellValue(key);
                row1.createCell(1).setCellValue(map.get(key));
                row++;
            }
            // 不加密导出
            try(FileOutputStream fos = new FileOutputStream("D://123.xlsx");){
                workbook.write(fos);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(workbook != null){
                    workbook.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public static double randomDouble(Double max,Double min){
        Random random = new Random();
        double v = random.nextDouble();
        if(!(v > min && v < max)){
            randomDouble(max,min);
        }
        return v;
    }

    public static String formateDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sf.format(date);
        return s;
    }
}
