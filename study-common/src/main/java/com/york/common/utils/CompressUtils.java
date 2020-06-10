package com.york.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @description: 压缩文件工具类
 * @author: york
 * @date: 2020-3-27 9:45
 * @version: <1.0>
 */
public class CompressUtils {

    private static final String FILE_SUFFIX = ".zip";

    public static void originalUncompressZip(String fileName,String output,Charset charset) throws Exception{
        File srcFile = new File(fileName);
        if(!srcFile.exists()){
            throw new Exception("文件不存在！");
        }
        if(!srcFile.isFile() || !fileName.endsWith(FILE_SUFFIX)){
            throw new Exception("文件类型不正确，只能是ZIP文件！");
        }
        // 处理字符集
        charset = charset == null ? StandardCharsets.UTF_8 : charset;
        // 解压目录（如没提供解压目录，将解压至该文件所属的目录+该文件名称创建的文件夹中）
        output = StringUtils.isNotBlank(output) ? output : fileName.substring(0,fileName.lastIndexOf(FILE_SUFFIX));
        System.out.println(output);
        try(
                // 加载Zip文件,并指定字符集文件，不同的压缩文件所用的字符集不一样
                // Windows平台Zip格式压缩所用字符集默认是GBK,Linux默认是UTF-8
                // 如不指定对应字符集，会出现以下错误
//                ZipFile zipFile = new ZipFile(fileName);
                ZipFile zipFile = new ZipFile(new File(fileName),ZipFile.OPEN_READ, charset);
                ){
            // 创建解压目录
            File outputDir = new File(output);
            if(!outputDir.exists()){
                // 如果目录不存在，同时创建父目录
                outputDir.mkdirs();
            }
            // 获取压缩文件中条目
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            // jdk6 写法
            while(entries.hasMoreElements()){
                ZipEntry zipEntry = entries.nextElement();
                String entryName = zipEntry.getName();
                System.out.println(entryName);
                if(zipEntry.isDirectory()){
//                    FileUtils.saveFile(output + entryName.substring(entryName.lastIndexOf("/")),zipFile.getInputStream(zipEntry));
//                    byte[] extra = zipEntry.getExtra();
                }else{
                    FileUtils.saveFile(output + entryName.substring(entryName.lastIndexOf("/")),zipFile.getInputStream(zipEntry));
                }
            }
            // jdk8 写法
//            Stream<? extends ZipEntry> stream = zipFile.stream();
//            stream.forEach(entry->{
//                zipFile.getInputStream(entry);
//            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            //        CompressUtils.originalUncompressZip("E:\\3D模型","",null);
            CompressUtils.originalUncompressZip("C://123/mongo.zip","",null);
//        CompressUtils.originalUncompressZip("C://software.zip","",Charset.forName("UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
