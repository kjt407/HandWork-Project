package com.handwork.comment.controller;


import com.handwork.comment.service.CommentService;
import com.handwork.comment.service.FCommentService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fcomment")
public class FCommentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  super.doPost(request, response);

        response.setContentType("application/x-json; charset=UTF-8");
        String type = request.getParameter("requestType");
        FCommentService service = new FCommentService();

        if (type != null && type.equals("load")) {
            // 게시글 상세페이지 로드시 댓글 불러오는 부분
            service.loadDB(request,response);
        } else {
            switch (type) {
                case "register" :
                    System.out.println("requestType: register");
                    service.registerDB(request, response);
                    break;
                case "edit" :
                    System.out.println("requestType: edit");
                    service.updateDB(request, response);
                    break;
                case "delete" :
                    System.out.println("requestType: delete");
                    service.deleteDB(request, response);
                    break;
            }
        }
        service.disconnect();
    }



}
