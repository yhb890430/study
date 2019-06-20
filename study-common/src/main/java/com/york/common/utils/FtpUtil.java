package com.york.common.utils;

import com.jcraft.jsch.SftpException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import sun.net.ftp.impl.FtpClient;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author York.yuan
 * @version <1.0>
 * @description FTP工具类
 * @createdate 2018/11/28 15:23
 **/
public final class FtpUtil {

    public static void main(String[] args) {
        // 不要使用sun.net.ftp.impl.FtpClient(里面需要telnet,但是在很多云服务器上外网telnet是被禁止的)
        // ftp ftps sftp的关系 https://www.cnblogs.com/Javi/p/6904587.html
        // apache commons-net包支持FTP/FTPS协议但不支持sftp协议
        // FTP FTPS SFTP 三者区别 https://blog.csdn.net/shmilychan/article/details/51848850
        FTPSClient client = new FTPSClient(Boolean.FALSE);
        try {
            client.connect("47.100.10.77",22);
            client.login("root","Dd45276#qv");
            FTPFile[] ftpFiles = client.listDirectories("/datadrawing");
            for (FTPFile ftpFile : ftpFiles) {
                System.out.println(ftpFile.getRawListing());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.logout();
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        SFTPUtil sftpUtil = new SFTPUtil("root","Dd45276#qv","47.100.10.77",22);
//        try {
//            sftpUtil.login();
//            sftpUtil.upload("/datadrawing","D://1.jpg");
//            sftpUtil.logout();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (SftpException e) {
//            e.printStackTrace();
//        }
    }

}
