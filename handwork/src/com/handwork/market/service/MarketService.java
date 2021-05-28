package com.handwork.market.service;


import com.handwork.market.entity.Market;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarketService {
	Connection conn;

	public MarketService(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://nfox.site:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
			String dbID = "handwork";
			String dbPassword = "handwork";
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public List<Market> getMarketList() {
		return getMarketList("title", "", 1);
	}

	public List<Market> getMarketList(int page) {
		return getMarketList("title", "", page);
	}

	public List<Market> getMarketList(String field, String query, int page) {

		List<Market> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM market A, " +
				"   (SELECT @ROWNUM := 0) R where "+field+" like ? order by regdate desc" +
				") " +
				"list WHERE num between ? and ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			stmt.setInt(2, 1+(page-1)*6);
			stmt.setInt(3, page*6);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				int id = rs.getInt("id");

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

				Market market = new Market(id, getBoardWrtierName(id), title, kategorie, location, period, price, content, regdate, hit, filename, how, writer_id, state, getCount(id), starAvg, topReview);

				list.add(market);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Market> getMarketList(String field, String query, int page, int limit) {

		List<Market> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM market A, " +
				"   (SELECT @ROWNUM := 0) R where "+field+" like ? order by regdate desc" +
				") " +
				"list WHERE num limit ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			stmt.setInt(2, limit);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				int id = rs.getInt("id");

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

				Market market = new Market(id, getBoardWrtierName(id), title, kategorie, location, period, price, content, regdate, hit, filename, how, writer_id, state, getCount(id), starAvg, topReview);

				list.add(market);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int getMarketCount() {
		return getMarketCount("title", "");
	}

	public int getMarketCount(String field, String query) {

		int count = 0;
		String sql="SELECT count(id) count " +
				"FROM( " +
				"SELECT @ROWNUM:=@ROWNUM+1  num, A.* FROM market A, " +
				"   (SELECT @ROWNUM := 0) R where "+field+" like ? order by regdate desc" +
				")A ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");

			ResultSet rs = stmt.executeQuery();

			if(rs.next())
				count = rs.getInt("count");

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	public Market getMarket(int id) {
		Market market = null;
		String sql = "select * from market where id=?";


		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String kategorie = rs.getString("kategorie");
				String location = rs.getString("location");
				String flocation = rs.getString("flocation");
				int period = rs.getInt("period");
				int price = rs.getInt("price");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int hit = rs.getInt("hit");
				String filename = rs.getString("filename");
				String how = rs.getString("how");
				String writer_id = rs.getString("writer_id");
				int state = rs.getInt("state");

				market = new Market(nid, getBoardWrtierName(nid), title, kategorie, location, flocation, period, price, content, regdate, hit, filename, how, writer_id, state);

				System.out.println(" ð  : " + regdate);

			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return market;
	}

	public Market getNextNotice(int id, boolean incHit) {
		Market notice = null;

		String sql = "select * from market" +
				"       where id = (" +
				"       select id from market" +
				"       where regdate > (select regdate from market where id = ?)" +
				"       limit 1" +
				"       )";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("id");
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

				notice = new Market(nid, getBoardWrtierName(nid), title, kategorie, location, period, price, content, regdate, hit, filename, how, writer_id, state);

				if(incHit) {
					stmt.execute("update market set hit=hit+1 where id=" + id);
				}
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return notice;
	}

	public Market getPrevNotice(int id, boolean incHit) {
		Market notice = null;

		String sql =   "select * from (select * from market order by regdate desc) A " +
				" where regdate < (select regdate from market where id = ?) " +
				" limit 1";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("id");
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

				notice = new Market(nid, getBoardWrtierName(nid), title, kategorie, location, period, price, content, regdate, hit, filename, how, writer_id, state);

				if(incHit) {
					stmt.execute("update market set hit=hit+1 where id=" + id);
				}
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return notice;
	}



	public int getCount(int boardNum){
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
	public String getBoardWrtierName(int boardNum){

		String id = null;
		String name = null;
		System.out.println(boardNum);
		try {

			String sql = "SELECT id, writer_id FROM market where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNum);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				id = rs.getString("writer_id");
			}

			sql = "select name from member where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				name = rs.getString("name");
			}
		} catch (Exception e) {

		}
		return name;
	}

	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}