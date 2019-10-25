package com.york.common.jdbc;

import com.googlecode.aviator.AviatorEvaluator;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Sql注入测试
 * @author: york
 * @date: 2019-10-12 16:14
 * @version: <1.0>
 */
public class SqlInjection {

    public static void main(String[] args) {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://139.198.190.182:3306/qiaohaoba?useUnicode=true&characterEncoding=utf-8", "qhbadmin", "zQ556677");
////            Statement st = con.createStatement();
////            ResultSet rs = st.executeQuery("SELECT * FROM `b_yh_user` WHERE id =" + "100 OR 1 = 1");
//
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM `b_yh_user` WHERE id = ?");
//            ps.setString(1,"100 OR 1 = 1");
//            // 打印PreparedStatement的语句，PreparedStatement在字符串参数上如何解决sql注入的问题
//            System.out.println(ps.toString());
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                String name = rs.getString(2);
//                System.out.println(name);
//            }
//            rs.close();
//            ps.close();
//            con.close();
            Map<String,Object> map = new HashMap<>();
            map.put("F",5786.2f);
            map.put("T",27.5f);
            Object res = AviatorEvaluator.execute("0.4002 * (F - 4547.2) + 13.0 * (T - 21)", map);
            System.out.println(res);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
