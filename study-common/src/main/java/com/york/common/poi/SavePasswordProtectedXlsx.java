package com.york.common.poi;

import com.york.common.poi.util.TempFileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.temp.EncryptedTempData;
import org.apache.poi.poifs.crypt.temp.SXSSFWorkbookWithCustomZipEntrySource;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

/**
 * @description:
 * @author: york
 * @date: 2019-9-17 10:30
 * @version: <1.0>
 */
public class SavePasswordProtectedXlsx {

    public static void main(String[] args) throws Exception {
//        TempFileUtils.checkTempFiles();
//        String filename = "D://123.xlsx";
//        String password = "123";
//        SXSSFWorkbookWithCustomZipEntrySource wb = new SXSSFWorkbookWithCustomZipEntrySource();
//        try {
//            for(int i = 0; i < 10; i++) {
//                SXSSFSheet sheet = wb.createSheet("Sheet" + i);
//                for(int r = 0; r < 1000; r++) {
//                    SXSSFRow row = sheet.createRow(r);
//                    for(int c = 0; c < 100; c++) {
//                        SXSSFCell cell = row.createCell(c);
//                        cell.setCellValue("abcd");
//                    }
//                }
//            }
//            EncryptedTempData tempData = new EncryptedTempData();
//            try {
//                wb.write(tempData.getOutputStream());
//                save(tempData.getInputStream(), filename, password);
//                System.out.println("Saved " + filename);
//            } finally {
//                tempData.dispose();
//            }
//        } finally {
//            wb.close();
//            wb.dispose();
//        }
//        TempFileUtils.checkTempFiles();

        SecureRandom sr = new SecureRandom();
        byte[] ivBytes = new byte[16];
        byte[] keyBytes = new byte[16];
        sr.nextBytes(ivBytes);
        sr.nextBytes(keyBytes);
        System.out.println(ivBytes);
        System.out.println(keyBytes);
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
