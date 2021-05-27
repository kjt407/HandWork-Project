package com.handwork.notice.controller;



import com.handwork.notice.entity.Notice;
import com.handwork.notice.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/notice")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		String category_ = request.getParameter("c");

		String field = "title";
		if(field_!=null && !field_.equals(""))
			field = field_;

		String query = "";
		if(query_ != null && !query_.equals(""))
			query = query_;

		int page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);


		NoticeService service = new NoticeService();

		List<Notice> list = service.getNoticeList(field, query, page);



		int count = service.getNoticeCount(field, query);

		request.setAttribute("list", list);
		request.setAttribute("count", count);

		service.disconnect();


		request.getRequestDispatcher("/WEB-INF/view/board/notice/notice.jsp").forward(request, response);
	}
}
