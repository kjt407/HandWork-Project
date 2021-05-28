package com.handwork.review.controller;


import com.handwork.dao.DAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.handwork.market.controller.MarketDetailController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/review/write")
public class ReviewWriteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //        super.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  super.doPost(request, response);
        request.setCharacterEncoding("utf-8");
        System.out.println("doPost 넘어옴!!");
        int star = Integer.parseInt(request.getParameter("radio-stars"));
        String content = request.getParameter("review-subs");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String id = (String) session.getAttribute("id");
        int board_num = Integer.parseInt(request.getParameter("board-num"));
        System.out.println(name);
        System.out.println(id);
        System.out.println(board_num);
        System.out.println(content);
        System.out.println(star);

        try {
            DAO dao = new DAO();
            Connection conn = dao.getConnection();

            String sql ="insert into review(user_id,content,regdate,board_num,name,star) values(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1,id);
            stmt.setString(2,content);
            stmt.setString(3,getCurrentTime());
            stmt.setInt(4,board_num);
            stmt.setString(5,name);
            stmt.setInt(6,star);

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            dao = null;
        } catch (Exception e) {
            // TODO: handle exception
        }
        response.sendRedirect(request.getContextPath()+"/market/detail?id="+board_num);

    }



    private String getCurrentTime() {

        return LocalDate.now() + " " + LocalTime.now().toString().substring(0, 8);
    }
}
