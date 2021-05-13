package com.handwork.controller.login;

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

@WebServlet("/kakaoLogin")
public class KakaoLoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String dept = request.getParameter("dept");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
	    String dbID = "handwork";
	    String dbPassword = "handwork";
		
		if(pw==null){
			try (Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(String.format("select * from member where id='%s'", id));) {
				if (rs.next()) {
				} else {
			stmt.executeUpdate(String.format("insert into member values(null, '%s', '%s', '%s', '%s','%s')", dept ,id, pw, name, email));
				}
			} catch (Exception e) {
			e.printStackTrace();
			}
		}

		try (Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(String.format("select * from member where id='%s' and pw='%s'",
						request.getParameter("id"), request.getParameter("pw")));) {

			if (rs.next()) {

				HttpSession session = request.getSession();
				session.setAttribute("id", rs.getString("id"));
				session.setAttribute("name", rs.getString("name"));
				session.setAttribute("dept", rs.getString("dept"));

				request.setAttribute("l", rs.getString("name"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
