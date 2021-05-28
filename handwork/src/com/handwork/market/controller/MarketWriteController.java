package com.handwork.market.controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/market/write")
public class MarketWriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		request.getRequestDispatcher("/WEB-INF/view/board/market/market_write.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder filename = new StringBuilder();

		int sizeLimit = 15 * 1024 * 1024;

		// ����θ� �����η� �����;�
		String realPath = request.getServletContext().getRealPath("upload/marketBoard");
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
		System.out.println("writer = " + writer);
		String title = multi.getParameter("title");
		String kategorie = multi.getParameter("kategorie");
		String location = multi.getParameter("location");
		String flocation = multi.getParameter("flocation");
		int period = Integer.parseInt(multi.getParameter("period"));
		int price = Integer.parseInt(multi.getParameter("price"));
		System.out.println(price);
		String content = multi.getParameter("content");
		String writer_id = (String)session.getAttribute("id");
		int state = Integer.parseInt(multi.getParameter("state"));

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

		System.out.println(filename);

		// int id = Integer.parseInt(multpartRequest.getParameter("id"));
		String how = multi.getParameter("how");
		try {

			String sql = "insert into market ( writer, title, kategorie, location, flocation, period, price, content, regdate, hit, filename, how, writer_id, state, latlng) "
					+ "values(?,?,?,?,?,?,?,?,?,0,?,?,?,?,?)";

			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://nfox.site:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
			String dbID = "handwork";
			String dbPassword = "handwork";
			// System.out.println(id);
			Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, kategorie);
			pstmt.setString(4, location);
			pstmt.setString(5, flocation);
			pstmt.setInt(6, period);
			pstmt.setInt(7, price);
			pstmt.setString(8, content);
			pstmt.setString(9, getCurrentTime());
			if (filename.toString().contains("null") || filename.toString() == null ) {
				pstmt.setNull(10, java.sql.Types.VARCHAR);
			} else {
				pstmt.setString(10, filename.toString());
			}
			pstmt.setString(11, how);
			pstmt.setString(12, writer_id);
			pstmt.setInt(13, state);
			pstmt.setString(14, getGeoCode(flocation));

			pstmt.executeUpdate();
			System.out.println(getCurrentTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(id);
		response.sendRedirect(request.getContextPath()+"/market");
	}

	private String getCurrentTime() {
		return LocalDate.now() + " " + LocalTime.now().toString().substring(0, 8);
	}

	private String getGeoCode(String flocation) {
		StringBuffer result = new StringBuffer();
		JSONObject obj = null;
		try {
			String address= URLEncoder.encode(flocation, "UTF-8");
			String apiKey= URLEncoder.encode("AIzaSyAY--fPA18-UuvufmlYYCPygAJYmQNXZVo", "UTF-8");
			String urlStr = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",address,apiKey);
			System.out.println(urlStr);
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			int rspCode = conn.getResponseCode();
			if (rspCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String nextLine = br.readLine();
				while ((nextLine = br.readLine()) != null) {
					result.append(nextLine);
				}
			}
			conn.disconnect();

			String resultStr = result.toString();
			resultStr = resultStr.substring(resultStr.indexOf("\"location\" : {"));
			resultStr = resultStr.substring(resultStr.indexOf("{"),resultStr.indexOf("}")+1);

			JSONParser jsonParse = new JSONParser();
			obj =  (JSONObject)jsonParse.parse(resultStr);

		} catch (IOException e) {
			System.out.println("RestCall Fail : " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Json error");
		}

		return String.valueOf(obj);
	}

}
