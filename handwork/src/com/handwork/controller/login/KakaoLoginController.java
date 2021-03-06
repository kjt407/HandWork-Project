package com.handwork.controller.login;

import com.handwork.dao.DAO;

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

		System.out.println("kakaoLogin 컨트롤러 doget 실행됨");

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		DAO dao = new DAO();
		Connection conn = dao.getConnection();

		try{
			if(pw==null){
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(String.format("select * from member where id='%s'", id));
				if (rs.next()) {
				} else {
					stmt.executeUpdate(String.format("insert into member(id,pw,name,email) values('%s', '%s', '%s','%s')", id, pw, name, email));
//					stmt.executeUpdate(String.format("insert into member values(null, '%s', '%s', '%s','%s')", id, pw, name, email));
				}
			}

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from member where id='%s' and pw='%s'",
			request.getParameter("id"), request.getParameter("pw")));
			conn.close();
			dao = null;

			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("id", rs.getString("id"));
				session.setAttribute("name", rs.getString("name"));

				request.setAttribute("l", rs.getString("name"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("kakaoLogin 컨트롤러 post 실행됨");
	}
}
