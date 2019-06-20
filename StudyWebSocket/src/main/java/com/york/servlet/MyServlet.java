package com.york.servlet;

import com.york.annotation.NotThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2018/11/27 9:51
 **/
@WebServlet(name = "myServlet",urlPatterns = {"/count","/init"})
public class MyServlet extends HttpServlet {

//    @NotThreadSafe
//    private Long count = 0L;
//
//    private AtomicLong count1 = new AtomicLong(0L);

    @Override
    @NotThreadSafe
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            // 证明Servlet是单例
//            System.out.println(this);
//            Thread.sleep(500L);
//            // 该行代码不是原子性操作
//            count++;
//            System.out.println(count);
            String token = req.getHeader("token");
        System.out.println(token);
        String token1 = req.getParameter("token");
        System.out.printf("token1 %s",token1);
//        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
//        resp.setContentType("text/plain;charset=utf-8");

        resp.getWriter().write("{\"name\":\"张三\",\"age\":20}");
        resp.getOutputStream().print(new String("{\"name\":\"张三\",\"age\":20}".getBytes(),"UTF-8"));
        resp.getOutputStream().write("{\"name\":\"张三\",\"age\":20}".getBytes("UTF-8"));
//        resp.getWriter().print("你好！");
//        resp.getWriter().write("我是大王！");
//        resp.getOutputStream().write(new String("你好！").getBytes("utf-8"));
//        resp.getWriter().write("你好！");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            Thread.sleep(500L);
//            Long val = count1.incrementAndGet();
//            System.out.println(val);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
