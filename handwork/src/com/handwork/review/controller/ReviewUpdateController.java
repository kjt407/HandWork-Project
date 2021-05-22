package com.handwork.review.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/review/update")
public class ReviewUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //        super.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  super.doPost(request, response);
        request.setCharacterEncoding("utf-8");
        System.out.println("update 메서드 실행");
        int star = Integer.parseInt(request.getParameter("radio-stars"));
        String content = request.getParameter("review-subs");
        int idx = Integer.parseInt(request.getParameter("review-idx"));
        int board_num = Integer.parseInt(request.getParameter("board-num"));

        System.out.println(star);
        System.out.println(content);
        System.out.println(idx);
        System.out.println(board_num);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
            String dbID = "handwork";
            String dbPassword = "handwork";

            String sql ="update review set content=? , star=? where idx=?";
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1,content);
            stmt.setInt(2,star);
            stmt.setInt(3,idx);

            stmt.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
        }
        response.sendRedirect(request.getContextPath()+"/market/detail?id="+board_num);
    }



    private String getCurrentTime() {

        return LocalDate.now() + " " + LocalTime.now().toString().substring(0, 8);
    }
}
