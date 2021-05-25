package com.handwork.mypage.controller;

import com.handwork.market.entity.Market;
import com.handwork.mypage.service.MyPageService;
import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;
import com.handwork.search.service.SearchService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mypage")
public class MyPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/view/mypage/mypage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-json; charset=UTF-8");
        String op = request.getParameter("op");

        switch (op) {
            case "board":
                response.getWriter().print(getBoard((String) request.getSession().getAttribute("id"), Integer.parseInt(request.getParameter("page"))));
                break;
            case "reply":
                response.getWriter().print(getReply((String) request.getSession().getAttribute("id"), Integer.parseInt(request.getParameter("page"))));
                break;
            case "info":
                response.getWriter().print(getInfo((String) request.getSession().getAttribute("id")));
                break;
            case "update":
                response.getWriter().print(updateInfo((String) request.getSession().getAttribute("id"), request));
                break;
        }
    }

    private JSONObject getBoard(String writer_id, int page) {
        MyPageService service = new MyPageService();
        JSONObject result = service.getBoardList(writer_id, page);
        service.disconnect();

        return result;
    }
    private JSONObject getReply(String writer_id, int page) {
        MyPageService service = new MyPageService();
        JSONObject result = service.getReplyList(writer_id, page);
        service.disconnect();

        return result;
    }

    private JSONObject getInfo(String writer_id) {
        MyPageService service = new MyPageService();
        JSONObject result = service.loadInfo(writer_id);
        service.disconnect();

        return result;
    }

    private JSONObject updateInfo(String writer_id, HttpServletRequest request) {
        MyPageService service = new MyPageService();
        JSONObject result = service.updateInfo(writer_id, request);
        service.disconnect();

        return result;
    }
    







}
