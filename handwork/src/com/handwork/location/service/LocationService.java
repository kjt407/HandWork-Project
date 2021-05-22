package com.handwork.location.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

public class LocationService {
	Connection conn;

	public LocationService() {
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

	public JSONArray getData(String option, String sql) {

		JSONArray jsonarray = new JSONArray();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			switch (option){
				case "init" :
					while(rs.next()){
						String board_num = rs.getString("id");
						String latlng = rs.getString("latlng");
						if(latlng != null && !latlng.trim().equals("")) {
//							JSONParser jsonParse = new JSONParser();
//							JSONObject jsonObj = (JSONObject) jsonParse.parse(latlng);
//							String x = (String) jsonObj.get("x");
//							String y = (String) jsonObj.get("y");

							JSONObject json = new JSONObject();
							json.put("board_num", board_num);
							json.put("latlng", latlng);
//							json.put("x", x);
//							json.put("y", y);

							jsonarray.add(json);
						}
					}
					break;

				case "click" :
					while(rs.next()){
						int board_num = rs.getInt("id");
						String writer = rs.getString("writer");
						String title = rs.getString("title");
						String kategorie = rs.getString("kategorie");
						String location = rs.getString("location");
						int period = rs.getInt("period");
						int price = rs.getInt("price");
						String content = rs.getString("content");
						String regdate = rs.getString("regdate");
						int hit = rs.getInt("hit");
						String filename = rs.getString("filename");
						String how = rs.getString("how");
						int state = rs.getInt("state");
						int starAvg = getStarAvg(board_num);

						JSONObject json = new JSONObject();
						json.put("board_num", board_num);
						json.put("writer", writer);
						json.put("title", title);
						json.put("category", kategorie);
						json.put("location", location);
						json.put("period", period);
						json.put("price", price);
						json.put("content", content);
						json.put("regdate", regdate);
						json.put("hit", hit);
						json.put("filename", filename);
						json.put("how", how);
						json.put("state", state);
						json.put("starAvg", starAvg);


						jsonarray.add(json);
						}
					break;
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonarray;
	}

	public int getStarAvg(int boardNum){
		int starAvg =0;
		try {
			String sql = "select round(avg(star))starAvg from review where board_num = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, boardNum);

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				starAvg = rs.getInt("starAvg");
			}
		} catch (Exception e) {

		}
		return starAvg;
	}

	public void disconnect(){
		try {
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}