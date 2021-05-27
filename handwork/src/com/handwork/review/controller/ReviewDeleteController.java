package com.handwork.review.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/review/delete")
public class ReviewDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //        super.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  super.doPost(request, response);
        request.setCharacterEncoding("utf-8");
        System.out.println("delete 메서드 실행");

        int idx = Integer.parseInt(request.getParameter("idx"));
        int board_num = Integer.parseInt(request.getParameter("board-num"));


        System.out.println(idx);
        System.out.println(board_num);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://nfox.site:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
            String dbID = "handwork";
            String dbPassword = "handwork";

            String sql ="delete from review where idx=?";
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,idx);


            stmt.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
        }
        response.sendRedirect(request.getContextPath()+"/market/detail?id="+board_num);
    }




}
