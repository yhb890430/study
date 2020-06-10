package com.york.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @description: 文件工具类
 * @author: york
 * @date: 2020-3-27 12:23
 * @version: <1.0>
 */
public class FileUtils {

    public static void saveFile(String fileName, InputStream is){
        try(FileOutputStream fos = new FileOutputStream(new File(fileName))){
            int len;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
