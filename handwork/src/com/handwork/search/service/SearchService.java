package com.handwork.search.service;

import com.handwork.dao.DAO;
import com.handwork.market.entity.Market;
import com.handwork.request.entity.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchService {
	Connection conn;
	DAO dao;

	public SearchService(){
		dao = new DAO();
		conn = dao.getConnection();
	}

	public JSONObject getRequestList(String type,String query,int page) {

		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();

		String sql = null;
		PreparedStatement stmt = null;

		try {
			if(type != null && type.equals("c")){
				sql="SELECT *" +
						"FROM( " +
						"SELECT @rownum:=@rownum+1  num, A.* FROM board A, " +
						"   (SELECT @ROWNUM := 0) R where kategorie = ? order by regdate desc" +
						") " +
						"list WHERE num between ? and ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, query);
				stmt.setInt(2, page*4-3);
				stmt.setInt(3, page*4);

			} else if(type != null && type.equals("q")){
				sql="SELECT *" +
						"FROM( " +
						"SELECT @rownum:=@rownum+1  num, A.* FROM board A, " +
						"   (SELECT @ROWNUM := 0) R where title like ? or content like ? order by regdate desc" +
						") " +
						"list WHERE num between ? and ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+query+"%");
				stmt.setString(2, "%"+query+"%");
				stmt.setInt(3, page*4-3);
				stmt.setInt(4, page*4);
			} else {
				return null;
			}

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				JSONObject obj = new JSONObject();
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
				int comment = getCommentCount(id);

				obj.put("idx",id);
				obj.put("writer",writer);
				obj.put("title",title);
				obj.put("category",kategorie);
				obj.put("location",location);
				obj.put("deadline",deadline);
				obj.put("price",price);
				obj.put("content",content);
				obj.put("regdate",regdate);
				obj.put("hit",hit);
				obj.put("filename",filename);
				obj.put("how",how);
				obj.put("writer_id",writer_id);
				obj.put("state",state);
				obj.put("comment",comment);

				array.add(obj);
			}
			result.put("list",array);
			if(page < getRequestPage(type,query)){
				result.put("next",true);
			} else {
				result.put("next",false);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int getCommentCount(int boardNum){
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

	public int getRequestPage(String type,String query){
		int maxPage = 0;
		try {
			PreparedStatement stmt = null;
			if(type != null && type.equals("c")){
				String sql = "SELECT count(*) FROM board where kategorie = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, query);
			} else if(type != null && type.equals("q")){
				String sql = "SELECT count(*) FROM board where title like ? or content like ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+query+"%");
				stmt.setString(2, "%"+query+"%");
			}

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int page = rs.getInt("count(*)");
				maxPage = (int) Math.ceil((double) page / 4);
			}
		} catch (Exception e) {
		}
		return maxPage;
	}



	public JSONObject getMarketList(String type,String query,int page) {

		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();

		String sql = null;
		PreparedStatement stmt = null;

		try {
			if(type != null && type.equals("c")){
				sql="SELECT *" +
						"FROM( " +
						"SELECT @rownum:=@rownum+1  num, A.* FROM market A, " +
						"   (SELECT @ROWNUM := 0) R where kategorie = ? order by regdate desc" +
						") " +
						"list WHERE num between ? and ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, query);
				stmt.setInt(2, page*4-3);
				stmt.setInt(3, page*4);

			} else if(type != null && type.equals("q")){
				sql="SELECT *" +
						"FROM( " +
						"SELECT @rownum:=@rownum+1  num, A.* FROM market A, " +
						"   (SELECT @ROWNUM := 0) R where title like ? or content like ? order by regdate desc" +
						") " +
						"list WHERE num between ? and ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+query+"%");
				stmt.setString(2, "%"+query+"%");
				stmt.setInt(3, page*4-3);
				stmt.setInt(4, page*4);
			} else {
				return null;
			}

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				JSONObject obj = new JSONObject();
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
				ArrayList list = getReviewData(id);
				int reviewnum = (int) list.get(0);
				int starAvg = (int) list.get(1);
				String topReview = (String) list.get(2);

				obj.put("idx",id);
				obj.put("writer",writer);
				obj.put("title",title);
				obj.put("category",kategorie);
				obj.put("location",location);
				obj.put("period",period);
				obj.put("price",price);
				obj.put("content",content);
				obj.put("regdate",regdate);
				obj.put("hit",hit);
				obj.put("filename",filename);
				obj.put("how",how);
				obj.put("writer_id",writer_id);
				obj.put("state",state);
				obj.put("reviewnum",reviewnum);
				obj.put("starAvg",starAvg);
				obj.put("topReview",topReview);

				System.out.println(obj);
				array.add(obj);
			}
			result.put("list",array);
			if(page < getMarketPage(type,query)){
				result.put("next",true);
			} else {
				result.put("next",false);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(array);
		return result;
	}

	private ArrayList getReviewData(int boardNum){
		ArrayList result = new ArrayList();

		try {
			String sql = "SELECT count(board_num) count, round(avg(star)) starAvg from review where board_num=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, boardNum);

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result.add(rs.getInt("count"));
				result.add(rs.getInt("starAvg"));
			}

			sql = "SELECT content FROM review where board_num=? order by idx desc limit 1";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, boardNum);

			rs = stmt.executeQuery();
			if(rs.next()) {
				result.add(rs.getString("content"));
			}
		} catch (Exception e) {
		}
		return result;
	}

	public int getMarketPage(String type,String query){
		int maxPage = 0;
		try {
			PreparedStatement stmt = null;
			if(type != null && type.equals("c")){
				String sql = "SELECT count(*) FROM market where kategorie = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, query);
			} else if(type != null && type.equals("q")){
				String sql = "SELECT count(*) FROM market where title like ? or content like ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+query+"%");
				stmt.setString(2, "%"+query+"%");
			}

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int page = rs.getInt("count(*)");
				maxPage = (int) Math.ceil((double) page / 4);
				System.out.println(maxPage);
			}
		} catch (Exception e) {
		}
		return maxPage;
	}

	public void disconnect(){
		try {
			conn.close();
			dao = null;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}