package com.handwork.request.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;

@WebServlet("/request")
public class RequestListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_!=null && !field_.equals(""))
			field = field_;
		String query = "";
		if(query_ != null && !query_.equals(""))
			query = query_;
		int page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		RequestService service = new RequestService();

		List<Request> list = service.getRequestList(field, query, page);



		int count = service.getRequestCount(field, query);

		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/request.jsp").forward(request, response);
		
	}
}
