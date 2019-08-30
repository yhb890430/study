package com.york.common.poi;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.util.Iterator;

/**
 * @description: Apache POI HSSF Excel demo
 * @author: york
 * @date: 2019-8-26 10:46
 * @version: <1.0>
 */
public class HSSFDemo {
    // java利用poi、Graphics2D技术，读取excel表格绘制成图片 https://blog.csdn.net/u014730287/article/details/80280480

    /**
     * HSSF组件读excel文件示例
     */
    public void readDemo(){
        FileInputStream fis = null;
        Workbook workbook = null;
        try {
            // TODO 可以研究下POIFSFileSystem 底层用的NIO中的FileChannel
            // TODO 关于加解密可以参考这个https://poi.apache.org/encryption.html
            // HSSFWorkbook只支持OLE2(xls)文件格式
            // 初始化Workbook(工作簿)
            // 方式一
            // 注意:设置解密密码一定要在创建Workbook之前执行,Biff8EncryptionKey这个类是线程安全的
            Biff8EncryptionKey.setCurrentUserPassword("123456");
            File file = new File("D://1.xls");
            fis = new FileInputStream(file);
//            workbook = new HSSFWorkbook(fis);
            // 方式二
            POIFSFileSystem fs = new POIFSFileSystem(file);
//            workbook = new HSSFWorkbook(fs);
            // 方式三
            DirectoryNode rootNode = fs.getRoot();
//            workbook = new HSSFWorkbook(rootNode, true);
            // 方式四:通过HSSFWorkbookFactory工厂类进行创建，HSSFWorkbookFactory继承自WorkbookFactory
            workbook = HSSFWorkbookFactory.createWorkbook(rootNode);
            // 方式五:通过WorkbookFactory工厂类进行创建,WorkbookFactory会自己根据文件判断创建是HSSFWorkbook还是XSSFWorkbook
            // 也可以通过WorkbookFactory的方法直接解密(推荐用这种方式创建，不需要自行判断是xls还是xlsx)
//            workbook = WorkbookFactory.create(file, "123456");
            // 一个Workbook可以有多个sheet
            // 根据索引获取sheet(索引从0开始)
//            Sheet sheet = workbook.getSheetAt(0);
            // 根据sheet名称获取sheet
            Sheet sheet = workbook.getSheet("学生");
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
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(fis != null){
                    fis.close();
                }
                if(workbook != null){
                    workbook.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void writeExcel(){

    }

}
