package com.handwork.market.controller;

import com.handwork.market.entity.Market;
import com.handwork.market.service.MarketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/market/detail")
public class MarketDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		MarketService service = new MarketService();

		Market market = service.getMarket(id);


		int count = service.getCount(id);
		
		String filename = market.getFilename();
		if(filename==null || filename.equals("")) {
			
		} else {
			String filenameRs = filename.substring(0, filename.length()-1);
			market.setFilename(filenameRs);
		}
		market.setIsUpdate(false);
		market.setCount(count);

		request.setAttribute("r", market);

		Market nextMarket = service.getNextNotice(id, true);
		request.setAttribute("nr", nextMarket);
		
		Market prevNotice_ = service.getPrevNotice(id, true);
		request.setAttribute("pr", prevNotice_);
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/board/market/market_view.jsp").forward(request, response);
		
	}
	
}
