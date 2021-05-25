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

@WebServlet("/mypage/user-img")
public class MyPageProfileImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/view/mypage/mypage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-json; charset=UTF-8");
        String op = request.getParameter("op");

        switch (op) {
            case "user-img":
                response.getWriter().print(getUserImg((String) request.getSession().getAttribute("id"), request));
                break;
        }
    }

    private JSONObject getUserImg(String writer_id, HttpServletRequest request) {
        MyPageService service = new MyPageService();
        JSONObject result = getProfileImgUpload(writer_id, request);
        service.disconnect();

        return result;
    }

    private JSONObject getProfileImgUpload(String writer_id, HttpServletRequest request){
        JSONObject obj = new JSONObject();

        int sizeLimit = 15 * 1024 * 1024;

        String realPath = request.getServletContext().getRealPath("upload/profile");

        File dir = new File(realPath);
        MultipartRequest multi = null;
        try {
            multi = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return obj;
    }




}
