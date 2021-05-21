package com.handwork.search.controller;

import com.handwork.market.entity.Market;
import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;
import com.handwork.search.service.SearchService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
		response.setContentType("application/x-json; charset=UTF-8");
		String op = request.getParameter("op");

		if (op == null || op.equals("")) {
			//pageInit
			request.getRequestDispatcher("WEB-INF/view/search/search.jsp").forward(request, response);
		} else {
			switch (op) {
				case "request":
					response.getWriter().print(getRequest(request.getParameter("type"), request.getParameter("query"), Integer.parseInt(request.getParameter("page"))));
					break;
				case "market":
					response.getWriter().print(getMarket(request.getParameter("type"), request.getParameter("query"), Integer.parseInt(request.getParameter("page"))));
					break;
			}
		}

	}

	private JSONObject getRequest(String type, String query, int page) {
		SearchService service = new SearchService();
		JSONObject result = service.getRequestList(type, query, page);
		service.disconnect();

		return result;
	}
	private JSONObject getMarket(String type, String query, int page) {
		SearchService service = new SearchService();
		JSONObject result = service.getMarketList(type, query, page);
		System.out.println(result);
		service.disconnect();

		return result;
	}

}