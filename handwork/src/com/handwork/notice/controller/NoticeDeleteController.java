package com.handwork.notice.controller;

import com.handwork.dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/notice/delete")
public class NoticeDeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			DAO dao = new DAO();
			Connection conn = dao.getConnection();

			Statement stmt = conn.createStatement();
			
			
			stmt.executeUpdate(String.format("delete from notice where id=" + id));
			  
			stmt.close();
			conn.close();
			dao = null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendRedirect(request.getContextPath()+"/notice");
	}
}
