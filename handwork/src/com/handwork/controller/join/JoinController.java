package com.handwork.controller.join;

import com.handwork.dao.DAO;

import java.io.IOException;
import java.sql.*;

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

		DAO dao = new DAO();
		Connection conn = dao.getConnection();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from member where id='%s'", id));
			if (rs.next()) {
				conn.close();
				dao = null;
				response.sendRedirect("joinFail");
			} else {
				stmt.executeUpdate(String.format("insert into member values(null, '%s', '%s', '%s', '%s', '%s', null, null)", dept, id, pw, name, email));
				conn.close();
				dao = null;
				response.sendRedirect("joinSuccess");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
