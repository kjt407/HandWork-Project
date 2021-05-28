package com.handwork.notice.service;

import com.handwork.dao.DAO;
import com.handwork.notice.entity.Notice;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeService {
	Connection conn;
	DAO dao;

	public NoticeService(){
		dao = new DAO();
		conn = dao.getConnection();
	}

	public List<Notice> getNoticeList() {
		return getNoticeList("title", "", 1);
	}

	public List<Notice> getNoticeList(int page) {
		return getNoticeList("title", "", page);
	}

	public List<Notice> getNoticeList(String field, String query, int page) {

		List<Notice> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM notice A, " +
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
				String writer = rs.getString("writer");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				String writer_id = rs.getString("writer_id");


				Notice notice = new Notice(id, category, writer, title, content, regdate, writer_id);

				list.add(notice);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}

	public int getNoticeCount(String field, String query) {

		int count = 0;
		String sql="SELECT count(id) count " +
				"FROM( " +
				"SELECT @ROWNUM:=@ROWNUM+1  num, A.* FROM notice A, " +
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

	public Notice getNotice(int id) {
		Notice notice = null;
		String sql = "select * from notice where id=?";


		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				String writer_id = rs.getString("writer_id");
				ArrayList list = getWriterInfo(writer_id);
				String writer = (String)list.get(1);


				notice = new Notice(nid, category, writer, title, content, regdate, writer_id);


			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return notice;
	}

	public JSONObject getContent(int id, String sessionID) {
		JSONObject obj = new JSONObject();

		try {
			String sql = "select content from notice where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				String content = rs.getString("content");
				obj.put("content",content);
			}
			if(sessionID != null && sessionID.equals("HANDWORK")){
				obj.put("admin",true);
			} else {
				obj.put("admin",false);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return obj;
	}


	public int getCount(int boardNum){
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

	public ArrayList getWriterInfo(String id){
		ArrayList result = new ArrayList();
		try {

			String sql = "SELECT profile_img,name from member where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, id);

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result.add(rs.getString("profile_img"));
				result.add(rs.getString("name"));
			}
		} catch (Exception e) {

		}
		return result;
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