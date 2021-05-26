package com.handwork.free.controller;

import com.handwork.free.entity.Free;
import com.handwork.free.service.FreeService;
import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/free")
public class FreeListController extends HttpServlet {
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

		FreeService service = new FreeService();

		List<Free> list = service.getFreeList(field, query, page);

		int count = service.getFreeCount(field, query);

		request.setAttribute("list", list);
		request.setAttribute("count", count);

		service.disconnect();


		request.getRequestDispatcher("/WEB-INF/view/board/free/free.jsp").forward(request, response);
		
	}
}
