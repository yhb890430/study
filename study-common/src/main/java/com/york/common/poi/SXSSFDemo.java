package com.york.common.poi;

import com.york.common.poi.util.TempFileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.temp.EncryptedTempData;
import org.apache.poi.poifs.crypt.temp.SXSSFWorkbookWithCustomZipEntrySource;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Random;

/**
 * @description: SXSSF用于解决Excel 2007及以后版本大量数据导出导致内存溢出的问题，不能用于大文件导入内存溢出
 * @author: york
 * @date: 2019-8-28 17:01
 * @version: <1.0>
 */
public class SXSSFDemo {
    // 参考文章
    // 解决导入内存溢出的问题 https://blog.csdn.net/daiyutage/article/details/53023020
    // 解决导出Excel 2007内存溢出问题:https://www.cnblogs.com/huangjian2/p/6238237.html
    // 使用阿里easyexcel 解决导入导出内存溢出问题(目前不是全解决)
    // https://www.iteye.com/blog/javaedge-yc-1308714

    /**
     * 使用SXSSFWorkbook导出大批量数据
     */
    public void exportLargeExcelDemo() throws IOException {
        // SXSSFWorkbook在内存中最多存储n行数据，当超过n行之后会将n行数据写入到一个临时的硬盘文件中，该参数通过rowAccessWindowSize指定
        // XSSFWorkbook是将所有数据都存储在内存中，然后一股脑的写入到硬盘中，很浪费内存
        // 经测试如果导出时不指定压缩临时文件参数，导出还要再快一些,所以不推荐压缩临时文件
        // 测试结果: 导出30万行数据，内存1000行，压缩临时文件，花费10s
        //           导出30万行数据，内存1000行，不压缩临时文件，花费7s,所用内存最大54m
        //           导出30万行数据，内存3000行，不压缩临时文件，花费8s,所用内存最大93m
        //           导出30万行数据，内存5000行，不压缩临时文件，花费7s，所用内存最大150m
        //           导出30万行数据，内存10000行，不压缩临时文件，花费7s，所用内存最大154m
        //           导出30万行数据，内存100000行，不压缩临时文件，花费7s，所用内存最大250m
        // 因为SXSSFWorkbook导出会产生临时文件，所以需要进行检查
        TempFileUtils.checkTempFiles();
        try(SXSSFWorkbook workbook = new SXSSFWorkbook(XSSFWorkbookFactory.createWorkbook(),3000,true,false);){
            Random random = new Random();
            SXSSFSheet sheet = workbook.createSheet();
            SXSSFRow titleRow = sheet.createRow(0);
            titleRow.createCell(0).setCellValue("姓名");
            titleRow.createCell(1).setCellValue("性别");
            titleRow.createCell(2).setCellValue("年龄");
            titleRow.createCell(3).setCellValue("省份");
            // 模拟生成30万行数据
            for(int i = 1;i < 300000;i++){
                SXSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue("jack"+i);
                row.createCell(1).setCellValue(i % 2 == 0 ? "女" : "男");
                row.createCell(2).setCellValue(random.nextInt(80));
                row.createCell(3).setCellValue(RandomStringUtils.random(4,'江','苏','省','浙','江','省'));
            }
            // 未加密导出
            try (FileOutputStream fos = new FileOutputStream("D://bigdata.xlsx")) {
                workbook.write(fos);
            } finally {
                // 清理临时文件
                workbook.dispose();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TempFileUtils.checkTempFiles();
    }

    /**
     * 大文件加密导出
     * @throws IOException
     */
    public void exportEncryptedLargeExcelDemo() throws IOException {
        TempFileUtils.checkTempFiles();
        try(SXSSFWorkbookWithCustomZipEntrySource workbook = new SXSSFWorkbookWithCustomZipEntrySource();){
            Random random = new Random();
            SXSSFSheet sheet = workbook.createSheet();
            SXSSFRow titleRow = sheet.createRow(0);
            titleRow.createCell(0).setCellValue("姓名");
            titleRow.createCell(1).setCellValue("性别");
            titleRow.createCell(2).setCellValue("年龄");
            titleRow.createCell(3).setCellValue("省份");
            // 模拟生成30万行数据
            for(int i = 1;i < 300000;i++){
                SXSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue("jack"+i);
                row.createCell(1).setCellValue(i % 2 == 0 ? "女" : "男");
                row.createCell(2).setCellValue(random.nextInt(80));
                row.createCell(3).setCellValue(RandomStringUtils.random(4,'江','苏','省','浙','江','省'));
            }
            // 加密导出
            EncryptedTempData tempData = new EncryptedTempData();
            try {
                workbook.write(tempData.getOutputStream());
                save(tempData.getInputStream(), "D://bigdata.xlsx", "123");
            } finally {
                tempData.dispose();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TempFileUtils.checkTempFiles();
    }

    public static void save(final InputStream inputStream, final String filename, final String pwd)
            throws InvalidFormatException, IOException, GeneralSecurityException {
        try (POIFSFileSystem fs = new POIFSFileSystem();
             OPCPackage opc = OPCPackage.open(inputStream);
             FileOutputStream fos = new FileOutputStream(filename)) {
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            Encryptor enc = Encryptor.getInstance(info);
            enc.confirmPassword(pwd);
            opc.save(enc.getDataStream(fs));
            fs.writeFilesystem(fos);
        } finally {
            if(inputStream != null){
                inputStream.close();
            }
        }
    }

}
