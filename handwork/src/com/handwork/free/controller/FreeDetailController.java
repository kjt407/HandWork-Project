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


@WebServlet("/free/detail")
public class FreeDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		FreeService service = new FreeService();
		
		Free free_ = service.getFree(id);


		int count = service.getCount(id);
		

		//free_.setIsUpdate(false);
		//free_.setCount(count);

		request.setAttribute("r", free_);
		
		Free nextFree_ = service.getNextFree(id, true);
		request.setAttribute("nr", nextFree_);

		Free prevFree_ = service.getPrevFree(id, true);
		request.setAttribute("pr", prevFree_);
		
		service.disconnect();
		
		request.getRequestDispatcher("/WEB-INF/view/board/free/free_view.jsp").forward(request, response);
		
	}
	
}
