package com.handwork.request.controller;

import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;




@WebServlet("/request/detail")
public class RequestDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		RequestService service = new RequestService();
		
		Request request_ = service.getRequest(id);


		int count = service.getCount(id);
		
		String filename = request_.getFilename();
		if(filename==null || filename.equals("")) {
			
		} else {
			String filenameRs = filename.substring(0, filename.length()-1);
			request_.setFilename(filenameRs);	
		}
		request_.setIsUpdate(false);
		request_.setCount(count);

		request.setAttribute("r", request_);
		
		Request nextRequest_ = service.getNextNotice(id, true);
		request.setAttribute("nr", nextRequest_);
		
		Request prevNotice_ = service.getPrevNotice(id, true);
		request.setAttribute("pr", prevNotice_);
		
		
		
		request.getRequestDispatcher("/request_view.jsp").forward(request, response);
		
	}
	
}
