package com.york.common.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.*;

/**
 * @author York.yuan@datadrawing.com
 * @description 邮件工具类
 * @create 2017/10/5 9:31
 **/
public final class EmailUtil {

    private static final Properties PROPERTIES = new Properties();

    private static String DEFAULT_CHARSET = StandardCharsets.UTF_8.name();

    private static String PROTOCOL;

    private static String HOST;

    private static String PORT;

    private static String DEFAULT_FROM ;

    private static String DEFAULT_PASSWORD;

    static {
        InputStream is = null;
        try {
            is = EmailUtil.class.getResourceAsStream("/email.properties");
            PROPERTIES.load(is);
            PROTOCOL =  PROPERTIES.getProperty("protocol");
            HOST = PROPERTIES.getProperty("host");
            PORT = PROPERTIES.getProperty("port");
            DEFAULT_FROM = PROPERTIES.getProperty("from");
            DEFAULT_PASSWORD = PROPERTIES.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭已经用完的资源
            try {
                PROPERTIES.clear();
                if(is != null){
                    is.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送邮件给单个收件人
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param from 发件人
     * @param password 发件人密码(用于验证)
     * @param to 收件人
     */
    public static void sendMail(String subject, String content, String from, String password, String to){
        List<String> list = new ArrayList<String>();
        list.add(to);
        sendMail(subject,content,from,password,list,null,null);
    }

    /**
     * 发送邮件给单个收件人,单个抄送人
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param from 发件人
     * @param password 发件人密码(用于验证)
     * @param to 收件人
     * @param cc 抄送人
     */
    public static void sendMail(String subject, String content, String from, String password, String to, String cc){
        List<String> list = new ArrayList<String>();
        list.add(to);
        List<String> ccList = new ArrayList<String>();
        ccList.add(cc);
        sendMail(subject,content,from,password,list,ccList,null);
    }

    /**
     * 发送邮件给单个收件人,单个抄送人，单个密送人
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param from 发件人
     * @param password 发件人密码(用于验证)
     * @param to 收件人
     * @param cc 抄送人
     * @param bcc 密送人
     */
    public static void sendMail(String subject, String content, String from, String password, String to, String cc, String bcc){
        List<String> list = new ArrayList<String>();
        list.add(to);
        List<String> ccList = new ArrayList<String>();
        ccList.add(cc);
        List<String> bccList = new ArrayList<String>();
        bccList.add(bcc);
        sendMail(subject,content,from,password,list,ccList,bccList);
    }

    /**
     * 发送邮件给多个收件人
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param from 发件人
     * @param password 发件人密码(用于验证)
     * @param to 收件人
     */
    public static void sendMail(String subject, String content, String from, String password, Collection<String> to){
        sendMail(subject,content,from,password,to,null,null);
    }

    /**
     * 发送邮件给多个收件人,多个抄送人
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param from 发件人
     * @param password 发件人密码(用于验证)
     * @param to 收件人
     * @param cc 抄送人
     */
    public static void sendMail(String subject, String content, String from, String password, Collection<String> to, Collection<String> cc){
        sendMail(subject,content,from,password,to,cc,null);
    }

    /**
     * 发送邮件给多个收件人,多个抄送人，多个密送人
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param from 发件人
     * @param password 发件人密码(用于验证)
     * @param to 收件人
     * @param cc 抄送人
     * @param bcc 密送人
     */
    public static void sendMail(String subject, String content, String from, String password, Collection<String> to, Collection<String> cc, Collection<String> bcc){
        //如果发件人或者发件人密码为空，就采用默认账号进行发送
        if(from == null || "".equals(from) || password == null || "".equals(password)){
            from = DEFAULT_FROM;
            password = DEFAULT_PASSWORD;
        }
        final String finalFrom = from;
        final String finalPwd = password;
        Properties p = new Properties();
        //指定发送采用协议
        p.setProperty("mail.transport.protocol",PROTOCOL);
        //指定发送邮件服务器
        p.setProperty("mail.smtp.host", HOST);
        //指定发送邮件是否需要认证
        p.setProperty("mail.smtp.auth", "true");
        //指定发送邮件服务器端口
        p.setProperty("mail.smtp.port",PORT);
        //如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类。默认值为true。
        p.setProperty("mail.smtp.socketFactory.fallback", "false");
        //指定发送邮件开启SSL校验
        p.put("mail.smtp.ssl.enable", "true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        //信任所有主机
        sf.setTrustAllHosts(true);
        //指定创建SSL套接字工厂
        p.put("mail.smtp.ssl.socketFactory",sf);
        //指定SSL套接字端口
        p.setProperty("mail.smtp.socketFactory.port", PORT);
        //创建会话
        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(finalFrom,finalPwd);
            }
        });
        //开启debug模式
        try {
            Message message = createMessage(session, from, subject, content, to, cc, bcc);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static Message createMessage(Session session, String from, String subject, String content, Collection<String> to, Collection<String> cc, Collection<String> bcc) throws  MessagingException,UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        //(1)发送人
        if(from == null || "".equals(from)){
            from = (String) PROPERTIES.get("from");
        }
        message.setFrom(new InternetAddress(from,"",DEFAULT_CHARSET));
        //(2)邮件主题
        message.setSubject(subject);
        //(3)邮件内容
        //(采用HTML的方式)
        message.setContent(content,"text/html;charset=UTF-8");
        //(4)收件人
        List<InternetAddress> list = null;
        list = orgReceviers(to);
        message.addRecipients(MimeMessage.RecipientType.TO,list.toArray(new InternetAddress[list.size()]));
        //(5)抄送人
        if(cc != null && cc.size() != 0){
            list = orgReceviers(cc);
            message.addRecipients(MimeMessage.RecipientType.CC,list.toArray(new InternetAddress[list.size()]));
        }
        //(6)密送人
        if(bcc != null && bcc.size() != 0){
            list = orgReceviers(bcc);
            message.addRecipients(MimeMessage.RecipientType.BCC,list.toArray(new InternetAddress[list.size()]));
        }
        message.saveChanges();
        return message;
    }

    private static List<InternetAddress> orgReceviers(Collection<String> collection) throws MessagingException,UnsupportedEncodingException {
        //去除重复收件人/抄送人/密送人
        HashSet<String> set = new HashSet<String>(collection);
        List<InternetAddress> receiverList = new ArrayList<InternetAddress>();
        for (String receiver : set) {
            receiverList.add(new InternetAddress(receiver,"",DEFAULT_CHARSET));
        }
        return receiverList;
    }

    public static void main(String[] args) {
        EmailUtil.sendMail("测试邮件", "22222", "information@datadrawing.com", "12QW34er", "York.yuan@datadrawing.com");
    }
}
