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

}
