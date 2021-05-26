package com.handwork.notice.controller;

import com.handwork.notice.entity.Notice;
import com.handwork.notice.service.NoticeService;
import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-json; charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String sessionID = (String) request.getSession().getAttribute("id");
		
		NoticeService service = new NoticeService();

		JSONObject result = service.getContent(id,sessionID);

		service.disconnect();
		
		response.getWriter().print(result);
	}
	
}
