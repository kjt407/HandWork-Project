package com.handwork.free.controller;

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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/free/write")
public class FreeWriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/board/free/free_write.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder filename = new StringBuilder();

		int sizeLimit = 15 * 1024 * 1024;

		// ����θ� �����η� �����;�
		String realPath = request.getServletContext().getRealPath("upload/freeBoard");
		System.out.println(realPath);

		// upload ������ ���� ��� ������ �����
		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdirs();

		MultipartRequest multi = null;
		multi = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String writer = (String) session.getAttribute("name");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String writer_id = (String)session.getAttribute("id");

		Enumeration files = multi.getFileNames();

		ArrayList<String> fileList = new ArrayList<String>();

		while (files.hasMoreElements()) {
			String filenames = multi.getFilesystemName((String) files.nextElement());
			fileList.add(filenames);
			// System.out.println(filenames);
		}
		for (int i = fileList.size(); i > 0; i--) {
			System.out.println(fileList.get(i - 1));
			filename.append(fileList.get(i - 1) + "/");
		}


		try {

			String sql = "insert into free ( writer, title, content, regdate, hit, filename, writer_id) "
					+ "values(?,?,?,?,0,?,?)";

			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
			String dbID = "handwork";
			String dbPassword = "handwork";
			// System.out.println(id);
			Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, getCurrentTime());
			if (filename.toString().contains("null") || filename.toString() == null ) {
				pstmt.setNull(5, java.sql.Types.VARCHAR);
			} else {
				pstmt.setString(5, filename.toString());
			}
			pstmt.setString(6, writer_id);

			pstmt.executeUpdate();
			System.out.println(getCurrentTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(id);
		response.sendRedirect(request.getContextPath()+"/free");
	}

	private String getCurrentTime() {
		return LocalDate.now() + " " + LocalTime.now().toString().substring(0, 8);
	}

}
