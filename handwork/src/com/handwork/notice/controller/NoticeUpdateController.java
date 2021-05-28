package com.handwork.notice.controller;

import com.handwork.notice.entity.Notice;
import com.handwork.notice.service.NoticeService;
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

@WebServlet("/notice/update")
public class NoticeUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-json; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		NoticeService service = new NoticeService();
		Notice notice_ = service.getNotice(id);
		request.setAttribute("r", notice_);
		service.disconnect();
		request.getRequestDispatcher("/notice/write").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-json; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		
		String writer = (String) session.getAttribute("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String category = request.getParameter("category");
		try {

		
		String sql = "update notice set title=?, content=?, category=? where id=?";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dbURL = "jdbc:mysql://nfox.site:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
	    String dbID = "handwork";
	    String dbPassword = "handwork";
		
		Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, category);
		pstmt.setInt(4, id);
		pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/notice");
	}
}

	

