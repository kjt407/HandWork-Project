package com.handwork.comment.controller;


import com.handwork.comment.entity.Comments;
import com.handwork.comment.service.CommentService;

import com.handwork.request.entity.Request;
import org.apache.catalina.tribes.group.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/comment")
public class CommentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //        super.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  super.doPost(request, response);

        response.setContentType("application/x-json; charset=UTF-8");
        System.out.println("doPost 넘어옴");

        String type = request.getParameter("requestType");
        System.out.println(type);
        CommentService service = new CommentService();

        if (type != null && type.equals("load")) {
            // 게시글 상세페이지 로드시 댓글 불러오는 부분
            System.out.println("type on load 들어옴");
            service.loadDB(request,response);
        } else {
            // 게시글 state가 채결완료인지 조건 검사
            if(service.getState(request,response) == 1){
                System.out.println("조건검사 state : 1");
                JSONObject jsonOb = new JSONObject();
                jsonOb.put("state", service.getState(request,response));
                jsonOb.put("boardNum", request.getParameter("boardNum"));
                response.getWriter().print(jsonOb);
                return;
            }
            // 게시글이 채결완료가 아니라면 아래 동작 시행
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
                case "adopt" :
                    System.out.println("requestType: adopt");
                    service.adoptDB(request, response);

            }
        }
        service.disconnect();
    }



}
