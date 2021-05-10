package com.handwork.request.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handwork.request.entity.Request;

@WebServlet("/request/delete")
public class RequestDeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
		    String dbID = "handwork";
		    String dbPassword = "handwork";
			
		    Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword); 
			Statement stmt = conn.createStatement();
			
			
			stmt.executeUpdate(String.format("delete from board where id=" + id));
			  
			    stmt.close();
			    conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendRedirect(request.getContextPath()+"/request");
	}
}
