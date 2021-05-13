package com.handwork.market.controller;

import com.handwork.market.entity.Market;
import com.handwork.market.service.MarketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/market")
public class MarketListController extends HttpServlet {
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
		
		MarketService service = new MarketService();

		List<Market> list = service.getMarketList(field, query, page);



		int count = service.getMarketCount(field, query);


		request.setAttribute("list", list);
		request.setAttribute("count", count);


		
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/board/market/market.jsp").forward(request, response);
		
	}
}
