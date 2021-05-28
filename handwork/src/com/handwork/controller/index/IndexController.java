package com.handwork.controller.index;

import com.handwork.market.entity.Market;
import com.handwork.market.service.MarketService;
import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;

import java.io.IOException;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("")
public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// LogInForm.jsp�� ������
		indexMarketList(request, response);
		indexRequestList(request, response);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void indexMarketList(HttpServletRequest request, HttpServletResponse response){
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

		MarketService service = new MarketService();

		int limit = 8; //8개만 가져올꺼임 ㅅㄱ
		List<Market> m_list = service.getMarketList(field, query, page, limit);



		int m_count = service.getMarketCount(field, query);


		request.setAttribute("m_list", m_list);
		request.setAttribute("m_count", m_count);
	}

	public void indexRequestList(HttpServletRequest request, HttpServletResponse response){
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

		int limit = 8; //8개만 가져올꺼임 ㅅㄱ
		List<Request> list = service.getRequestList(field, query, page, limit);



		int count = service.getRequestCount(field, query);

		request.setAttribute("list", list);
		request.setAttribute("count", count);
	}

}
