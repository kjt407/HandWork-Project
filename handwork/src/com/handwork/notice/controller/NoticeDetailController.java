package com.handwork.notice.controller;

import com.handwork.notice.entity.Notice;
import com.handwork.notice.service.NoticeService;
import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;

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
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		NoticeService service = new NoticeService();
		
		Notice notice_ = service.getNotice(id);


		int count = service.getCount(id);
		


		request.setAttribute("r", notice_);
		
		Notice nextRequest_ = service.getNextNotice(id, true);
		request.setAttribute("nr", nextRequest_);

		Notice prevNotice_ = service.getPrevNotice(id, true);
		request.setAttribute("pr", prevNotice_);
		
		service.disconnect();
		
		request.getRequestDispatcher("/WEB-INF/view/board/notice/notice_view.jsp").forward(request, response);
		
	}
	
}
