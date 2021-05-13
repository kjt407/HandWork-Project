package com.handwork.market.controller;

import com.handwork.market.entity.Market;
import com.handwork.market.service.MarketService;
import com.handwork.review.entity.Reviews;
import com.handwork.review.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/market/detail")
public class MarketDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("리뷰리스트 테스트");
		MarketService service = new MarketService();
		ReviewService reviewService = new ReviewService();

		Market market = service.getMarket(id);

		List<Reviews> reviewsList = reviewService.getReviewsList(id);


		int count = service.getCount(id);
		int starAvg = service.getStarAvg(id);
		
		String filename = market.getFilename();
		if(filename==null || filename.equals("")) {
			
		} else {
			String filenameRs = filename.substring(0, filename.length()-1);
			market.setFilename(filenameRs);
		}
		market.setIsUpdate(false);
		market.setCount(count);

		market.setStarAvg(starAvg);


		request.setAttribute("reviewsList", reviewsList);
		request.setAttribute("r", market);

		Market nextMarket = service.getNextNotice(id, true);
		request.setAttribute("nr", nextMarket);
		
		Market prevNotice_ = service.getPrevNotice(id, true);
		request.setAttribute("pr", prevNotice_);



		request.getRequestDispatcher("/WEB-INF/view/board/market/market_view.jsp").forward(request, response);
		
	}


	
}
