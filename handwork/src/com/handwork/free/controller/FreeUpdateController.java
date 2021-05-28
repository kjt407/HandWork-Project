package com.handwork.free.controller;

import com.handwork.free.entity.Free;
import com.handwork.free.service.FreeService;
import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/free/update")
public class FreeUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);

		FreeService service = new FreeService();

		Free free_ = service.getFree(id);
		//free_.setIsUpdate(true);

		request.setAttribute("r", free_);
		service.disconnect();
		request.getRequestDispatcher("/free/write").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");


		try {
			String sql = "update free set title=?, content=? where id=?";

			Class.forName("com.mysql.cj.jdbc.Driver");
				String dbURL = "jdbc:mysql://nfox.site:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
			String dbID = "handwork";
			String dbPassword = "handwork";

			Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			PreparedStatement pstmt = conn.prepareStatement(sql);


			pstmt.setString(1, request.getParameter("title"));
			pstmt.setString(2, request.getParameter("content"));
			pstmt.setInt(3, Integer.parseInt(request.getParameter("id")));

			pstmt.executeUpdate();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath()+"/free");
	}
}


