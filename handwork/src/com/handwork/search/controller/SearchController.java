package com.handwork.search.controller;

import com.handwork.market.entity.Market;
import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;
import com.handwork.search.service.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String query_ = request.getParameter("q");
		String category_ = request.getParameter("c");

		final int isQ = 0;
		final int isC = 1;
		int option = 99;

		String query = "";
		if(query_ != null && !query_.equals("")){
			query = query_;
			option = isQ;
		}

		String category = "";
		if(category_ != null && !category_.equals("")){
			category = category_;
			option = isC;
		}

		SearchService service = new SearchService();

		List<Request> requestList;
		List<Market> marketList;

		//조건 사용하여 카테고리 검색인지 전체검색인지 구분하기
		if(option == isQ){
//			전체검색일때
			System.out.println("전체검색");
		} else if(option == isC){
//			카테고리 검색일때
			System.out.println("카테고리검색");
		}
		requestList = service.getRequestSearchList(query);
		requestList = service.getRequestCategorySearchList(category);
		marketList = service.getMarketSearchList(query);
		marketList = service.getMarketCategorySearchList(query);

		request.setAttribute("rlist", requestList);
		request.setAttribute("mlist", marketList);
		request.getRequestDispatcher("/WEB-INF/view/search/search.jsp").forward(request, response);
		
	}
}
