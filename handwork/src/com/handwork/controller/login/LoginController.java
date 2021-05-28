package com.handwork.controller.login;

import com.handwork.dao.DAO;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// LogInForm.jsp�� ������
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/login/login.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		DAO dao = new DAO();
		Connection conn = dao.getConnection();

		try {
			if (pw == null) {
				Statement stmt = null;
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(String.format("select * from member where id='%s'", id));
				if (rs.next()) {
				} else {
					stmt.executeUpdate(String.format("insert into member values(null, '%s', '%s', '%s', '%s')", id, pw, name, email));
				}
			}

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from member where id='%s' and pw='%s'", request.getParameter("id"), request.getParameter("pw")));
			conn.close();
			dao = null;
			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("id", rs.getString("id"));
				session.setAttribute("name", rs.getString("name"));
				session.setAttribute("dept", rs.getString("dept"));

				request.setAttribute("l", rs.getString("name"));
				response.sendRedirect(request.getContextPath() + "/");
				return;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		response.sendRedirect("loginFail");
	}
}