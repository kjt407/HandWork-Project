package com.handwork.mypage.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class MyPageService {
	Connection conn;

	public MyPageService(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
			String dbID = "handwork";
			String dbPassword = "handwork";
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public JSONObject getBoardList(String writer_id,int page) {

		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();

		String sql = null;
		PreparedStatement stmt = null;

		try {

			System.out.println("sql문실행전");
				sql="select * from (" +
						"SELECT id, content, regdate, title FROM board WHERE writer_id = ? " +
						"UNION ALL " +
						"SELECT id, content, regdate, title FROM market WHERE writer_id = ? " +
						") a order by regdate desc limit 0,5";
				stmt = conn.prepareStatement(sql);
			System.out.println("sql문실행후"+sql);
			stmt.setString(1, writer_id);
			stmt.setString(2, writer_id);
			System.out.println("sql문실행후후");
			//stmt.setInt(3, 5*page-5);

			ResultSet rs = stmt.executeQuery();
			System.out.println("sql문실행후후ㅇㄴㅇㄴ");
			while(rs.next()){
				JSONObject obj = new JSONObject();
				String title = rs.getString("title");
				String regdate = rs.getString("regdate");

				System.out.println("asdasdasddasdsaasdasdasdsad");
				System.out.println(title);
				System.out.println(regdate);

				obj.put("title",title);
				obj.put("regdate",regdate);

				array.add(obj);
			}
			result.put("list",array);
			System.out.println(result.toJSONString());
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public JSONObject getReplyList(String writer_id,int page) {

		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();

		String sql = null;
		PreparedStatement stmt = null;

		try {
			System.out.println("reply sql문실행전");
			sql="select * from (" +
					"SELECT idx, content, regdate, user_id FROM review WHERE user_id = ? " +
					"UNION ALL " +
					"SELECT idx, content, regdate, user_id FROM comment WHERE user_id = ? " +
					") a order by regdate  desc limit ?,5";
				stmt = conn.prepareStatement(sql);
			stmt.setString(1, writer_id);
			stmt.setString(2, writer_id);
			stmt.setInt(3, 5*page-5);

			System.out.println("reply sql문실행후"+sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("reply sql문실행후후후ㅜㅎ");
			while(rs.next()){
				JSONObject obj = new JSONObject();
				int idx = rs.getInt("idx");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				String writer = rs.getString("writer_id");

				obj.put("idx",idx);
				obj.put("content",content);
				obj.put("regdate",regdate);
				obj.put("writer",writer);


				System.out.println(obj);
				array.add(obj);
			}
			result.put("list",array);

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(result.toJSONString());
		return result;
	}

	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


}