package com.handwork.mypage.controller;

import com.handwork.dao.DAO;
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
        JSONObject result = getProfileImgUpload(writer_id, request);

        return result;
    }

    private JSONObject getProfileImgUpload(String writer_id, HttpServletRequest request){
        JSONObject obj = new JSONObject();

        int sizeLimit = 15 * 1024 * 1024;
        String realPath = request.getServletContext().getRealPath("upload/profile");
        MultipartRequest multi = null;
        try {
            multi = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
            String editProfileImg = multi.getFilesystemName("img");

            String sql = "update member set profile_img=? where id=?";
            DAO dao = new DAO();
            Connection conn = dao.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, editProfileImg);
            pstmt.setString(2, writer_id);
            pstmt.executeUpdate();

            conn.close();
            dao = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }




}