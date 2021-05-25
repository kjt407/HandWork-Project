package com.handwork.mypage.controller;

import com.handwork.mypage.service.MyPageService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet("/mypage/userimg")
public class MyPageProfileImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet 실행");
        request.getRequestDispatcher("WEB-INF/view/mypage/mypage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost 실행");
        response.setContentType("application/x-json; charset=UTF-8");

        response.getWriter().print(getUserImg((String) request.getSession().getAttribute("id"), request));


    }

    private JSONObject getUserImg(String writer_id, HttpServletRequest request) {
        MyPageService service = new MyPageService();
        System.out.println("0번");
        JSONObject result = getProfileImgUpload(writer_id, request);
        service.disconnect();

        return result;
    }

    private JSONObject getProfileImgUpload(String writer_id, HttpServletRequest request){
        JSONObject obj = new JSONObject();

        int sizeLimit = 15 * 1024 * 1024;
        String realPath = request.getServletContext().getRealPath("upload/profile");
        MultipartRequest multi = null;
        try {
            System.out.println("1번");
            multi = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
            System.out.println("2번");
            String editProfileImg = multi.getParameter("img");
            System.out.println("editProfileImg : " + editProfileImg);
            System.out.println("3번");


            Enumeration e = multi.getParameterNames();


            while ( e.hasMoreElements() ){
                String name = (String) e.nextElement();
                String[] values = request.getParameterValues(name);
                for (String value : values) {
                    System.out.println("name=" + name + ",value=" + value);
                }
            }

            String sql = "update member set profile_img=? where id=?";
            System.out.println("ㅎㅎㅎㅎㅎㅎㅎㅎ");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
            String dbID = "handwork";
            String dbPassword = "handwork";
            // System.out.println(id);
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, editProfileImg);
            pstmt.setString(2, writer_id);

            pstmt.executeUpdate();
            System.out.println("ㅎㅎㅎㅎㅎㅎㄴㄴㄴㄴㄴㄴㅎㅎ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }




}
