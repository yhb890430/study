package com.york.common.poi.util;

import org.apache.poi.util.TempFile;

import java.io.File;
import java.io.IOException;

/**
 * @description: POI临时文件检测工具类
 * @author: york
 * @date: 2019-9-16 13:29
 * @version: <1.0>
 */
public final class TempFileUtils {

    private TempFileUtils() {
    }

    public static void checkTempFiles() throws IOException {
        String tmpDir = System.getProperty(TempFile.JAVA_IO_TMPDIR) + "/poifiles";
        File tempDir = new File(tmpDir);
        if(tempDir.exists()) {
            String[] tempFiles = tempDir.list();
            if(tempFiles != null && tempFiles.length > 0) {
                System.out.println("found files in poi temp dir " + tempDir.getAbsolutePath());
                for(String filename : tempFiles) {
                    System.out.println("file: " + filename);
                }
            }
        } else {
            System.out.println("unable to find poi temp dir");
        }
    }
}
