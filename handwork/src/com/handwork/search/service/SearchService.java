package com.handwork.search.service;

import com.handwork.market.entity.Market;
import com.handwork.request.entity.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchService {
	Connection conn;

	public SearchService(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
			String dbID = "handwork";
			String dbPassword = "handwork";
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public List<Request> getRequestSearchList() {
		return getRequestSearchList("");
	}

	public List<Request> getRequestSearchList(String query) {

		List<Request> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM board A, " +
				"   (SELECT @ROWNUM := 0) R where title like ? or content like ? order by regdate desc" +
				") " +
				"list WHERE num between 1 and 6";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			stmt.setString(2, "%"+query+"%");
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
//             int id = rs.getInt("id");
//               String title = rs.getString("title");
//               Date regdate = rs.getDate("regdate");
//               String writer_id = rs.getString("writer_id");
//               int hit = rs.getInt("hit");
//               String files = rs.getString("files");
//               String content = rs.getString("content");
//
				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String kategorie = rs.getString("kategorie");
				String location = rs.getString("location");
				String deadline = rs.getString("deadline");
				int price = rs.getInt("price");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int hit = rs.getInt("hit");
				String filename = rs.getString("filename");
				String how = rs.getString("how");
				String writer_id = rs.getString("writer_id");
				int state = rs.getInt("state");

				Request request = new Request(id, writer, title, kategorie, location, deadline, price, content, regdate, hit, filename, how, writer_id, state, getRequestCount(id));

				list.add(request);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public List<Request> getRequestCategorySearchList(String category) {

		List<Request> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM board A, " +
				"   (SELECT @ROWNUM := 0) R where kategorie like ? order by regdate desc" +
				") " +
				"list WHERE num between 1 and 6";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+category+"%");
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String kategorie = rs.getString("kategorie");
				String location = rs.getString("location");
				String deadline = rs.getString("deadline");
				int price = rs.getInt("price");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int hit = rs.getInt("hit");
				String filename = rs.getString("filename");
				String how = rs.getString("how");
				String writer_id = rs.getString("writer_id");
				int state = rs.getInt("state");

				Request request = new Request(id, writer, title, kategorie, location, deadline, price, content, regdate, hit, filename, how, writer_id, state, getRequestCount(id));

				list.add(request);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}



	public int getRequestCount(int boardNum){
		System.out.println("getCount 메서드 실행");
		int count =0;
		try {
			String sql = "SELECT count(board_num) count from comment where board_num=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, boardNum);

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {

		}
		return count;
	}




	public List<Market> getMarketSearchList() {
		return getMarketSearchList("");
	}

	public List<Market> getMarketSearchList(String query) {

		List<Market> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM market A, " +
				"   (SELECT @ROWNUM := 0) R where title like ? or content like ? order by regdate desc" +
				") " +
				"list WHERE num between 1 and 6";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			stmt.setString(2, "%"+query+"%");
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				int id = rs.getInt("id");
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
				String writer_id = rs.getString("writer_id");
				int state = rs.getInt("state");
				int starAvg = getStarAvg(id);
				String topReview = topReview(id);

				Market market = new Market(id, writer, title, kategorie, location, period, price, content, regdate, hit, filename, how, writer_id, state, getMarketCount(id), starAvg, topReview);

				list.add(market);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Market> getMarketCategorySearchList(String query) {

		List<Market> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM market A, " +
				"   (SELECT @ROWNUM := 0) R where kategorie like ? order by regdate desc" +
				") " +
				"list WHERE num between 1 and 6";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				int id = rs.getInt("id");
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
				String writer_id = rs.getString("writer_id");
				int state = rs.getInt("state");
				int starAvg = getStarAvg(id);
				String topReview = topReview(id);

				Market market = new Market(id, writer, title, kategorie, location, period, price, content, regdate, hit, filename, how, writer_id, state, getMarketCount(id), starAvg, topReview);

				list.add(market);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	public int getMarketCount(int boardNum){
		System.out.println("getCount 메서드 실행");
		int count =0;
		try {
			String sql = "SELECT count(board_num) count from review where board_num=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, boardNum);

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {

		}
		return count;
	}

	public int getStarAvg(int boardNum){
		System.out.println("getStarAvg 메서드 실행");
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

	public String topReview(int boardnum){
		String content ="";
		try {
			String sql = "SELECT content FROM review where board_num=? order by idx desc limit 1";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, boardnum);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				content = rs.getString("content");
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return content;
	}

	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


}