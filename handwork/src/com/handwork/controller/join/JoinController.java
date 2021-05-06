package com.handwork.controller.join;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/join")
public class JoinController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// LogInForm.jsp�� ������
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/login/join.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String dept = request.getParameter("user-type");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 + "@" + email2;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
	    String dbID = "handwork";
	    String dbPassword = "handwork";
		
		try (Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(String.format("select * from member where id='%s'", id));) {
			if (rs.next()) {
				response.sendRedirect("joinFail");
		
			} else {
		stmt.executeUpdate(String.format("insert into member values(null, '%s', '%s', '%s', '%s', '%s')", dept, id, pw, name, email));
		
		response.sendRedirect("joinSuccess");
		
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}
}
