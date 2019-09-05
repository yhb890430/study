package com.york.common.poi;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @description: Apache POI XSSF Excel demo
 * @author: york
 * @date: 2019-8-28 16:49
 * @version: <1.0>
 */
public class XSSFDemo {
    // 官方示例代码：https://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/
    /**
     * XSSF组件读excel文件示例
     */
    public void readDemo(){
        InputStream is = null;
        Workbook workbook = null;
        try {
            // XSSFWorkbook支持OOXML(XLSX和XLSM)文件格式，XLSM为启用宏的OOXML格式
            // xlsx最多1048576行，最多16384列
            // 初始化Workbook(工作簿)
            // 方式一
            File file = new File("D://24.xlsx");
            is = new FileInputStream(file);
            // 解密带密码XLSX文件，不解密会报错
//            POIFSFileSystem fs = new POIFSFileSystem(is);
//            EncryptionInfo info = new EncryptionInfo(fs);
//            Decryptor d = Decryptor.getInstance(info);
//            if (!d.verifyPassword("123456")) {
//                throw new RuntimeException("incorrect password");
//            }
//            is = d.getDataStream(fs);
//            workbook = new XSSFWorkbook(file);
//            workbook = new XSSFWorkbook(is);
            // 方式二
//            workbook = new XSSFWorkbook(OPCPackage.open(file));
//            workbook = new XSSFWorkbook(OPCPackage.open(is));
            // 方式三
//            workbook = XSSFWorkbookFactory.createWorkbook(file,false);
//            workbook = XSSFWorkbookFactory.createWorkbook(is);
//            workbook = XSSFWorkbookFactory.create(OPCPackage.open(file));
//            workbook = XSSFWorkbookFactory.createWorkbook(OPCPackage.open(file));
            // 方式四(推荐用这种方式创建，不需要自行判断是xls还是xlsx，也能兼容上述另存的问题)
            // 还可以通过以下方法进行解密，不需要写那么一长串
//            workbook = WorkbookFactory.create(file,"123456");
            workbook = WorkbookFactory.create(file);
            // 获取Sheet
//            Sheet sheet = workbook.getSheet("sheetname");
            Sheet sheet = workbook.getSheetAt(0);
            // 获取有内容的第一行和最后一行的索引(从0开始，+1等于行号)
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            System.out.printf("第一行索引:%d\n",firstRowNum);
            System.out.printf("最后一行索引:%d\n",lastRowNum);
            // 获取有值物理总行数,合并行有内容的合并是几行就被计算成几行物理行
            // 注意:不能用这个作为总行数去进行遍历，这个返回的是所有有内容的行，可能中间有几行没有内容，那么返回的行数肯定是小于最后一行的行号的
            // 总物理行数<= 最后一行行号(最后一行索引 + 1)
            int totalRows = sheet.getPhysicalNumberOfRows();
            System.out.printf("总物理行数<= 最后一行行号(最后一行索引 + 1):%d\n",totalRows);
            // 通过索引获取某一行数据
//            Row row = sheet.getRow(0);
            // 建议通过以下方式获取所有行（经测试不会返回没有任何内容的行）
//            Iterator<Row> rowIterator = sheet.iterator();
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                // 获取行号
                // 发现合并单元格的内容算在该合并单元格第一个物理单元格下
                int rowNum = row.getRowNum();
                System.out.println("行号:"+rowNum);
                // 获取一行里面有内容的第一个单元格和最后一个单元格的索引(从0开始，+1等于列号)
//                short firstCellNum = row.getFirstCellNum();
//                short lastCellNum = row.getLastCellNum();
//                System.out.printf("某行第一个有内容单元格索引:%d\n",firstCellNum);
//                System.out.printf("某行最后一个有内容单元格索引:%d\n",lastCellNum);
                // 获取一行中的所有有内容单元格
//                Iterator<Cell> cellIterator = row.iterator();
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    // 获取单元格上的批注
                    Comment comment = cell.getCellComment();
                    if(comment != null){
                        System.out.println(comment.getString().getString());
                    }
                    // 获取单元格类型
                    CellType cellType = cell.getCellType();
                    // 根据单元格类型去调用不同的方法
                    switch (cellType){
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue());
                            break;
                        case STRING:
                            System.out.println(cell.getStringCellValue());
                            break;
                        case BOOLEAN:
                            System.out.println(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            System.out.println(cell.getCellFormula());
                            break;
                        case BLANK:
                            System.out.println("空");
                        default:
                            break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
                if(workbook != null){
                    workbook.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试读取大Excel文件
     */
    public void readLargeExcel(){
        // 读取文件时就直接内存溢出
        // Caused by: java.lang.OutOfMemoryError: GC overhead limit exceeded
        Workbook workbook = null;
        try{
            workbook = WorkbookFactory.create(new File("D://2007.xlsx"));
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//                while (cellIterator.hasNext()){
//                    Cell cell = cellIterator.next();
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(workbook != null){
                    workbook.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
