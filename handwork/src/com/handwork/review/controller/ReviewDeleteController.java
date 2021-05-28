package com.handwork.review.controller;


import com.handwork.dao.DAO;

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
            DAO dao = new DAO();
            Connection conn = dao.getConnection();

            String sql ="delete from review where idx=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,idx);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            dao = null;
        } catch (Exception e) {
            // TODO: handle exception
        }
        response.sendRedirect(request.getContextPath()+"/market/detail?id="+board_num);
    }




}
