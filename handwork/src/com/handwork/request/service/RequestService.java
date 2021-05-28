package com.handwork.request.service;

import com.handwork.request.entity.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestService {

	Connection conn;

	public RequestService(){
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

	public List<Request> getRequestList() {
		return getRequestList("title", "", 1);
	}

	public List<Request> getRequestList(int page) {
		return getRequestList("title", "", page);
	}

	public List<Request> getRequestList(String field, String query, int page) {

		List<Request> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM board A, " +
				"   (SELECT @ROWNUM := 0) R where "+field+" like ? order by regdate desc" +
				") " +
				"list WHERE num between ? and ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			stmt.setInt(2, 1+(page-1)*5);
			stmt.setInt(3, page*5);
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


				Request request = new Request(id, getBoardWrtierName(id), title, kategorie, location, deadline, price, content, regdate, hit, filename, how, writer_id, state, getCount(id));

				list.add(request);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public List<Request> getRequestList(String field, String query, int page, int limit) {

		List<Request> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM board A, " +
				"   (SELECT @ROWNUM := 0) R where "+field+" like ? order by regdate desc" +
				") " +
				"list WHERE num limit ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			stmt.setInt(2, limit);

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


				Request request = new Request(id, getBoardWrtierName(id), title, kategorie, location, deadline, price, content, regdate, hit, filename, how, writer_id, state, getCount(id));

				list.add(request);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int getRequestCount() {
		return getRequestCount("title", "");
	}

	public int getRequestCount(String field, String query) {

		int count = 0;
		String sql="SELECT count(id) count " +
				"FROM( " +
				"SELECT @ROWNUM:=@ROWNUM+1  num, A.* FROM board A, " +
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

	public Request getRequest(int id) {
		Request request = null;
		String sql = "select * from board where id=?";


		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("id");
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
				ArrayList list = getWriterInfo(writer_id);
				String profile_img = (String)list.get(0);
				String writer = (String)list.get(1);


				request = new Request(nid, writer, title, kategorie, location, deadline, price, content, regdate, hit, filename, how, writer_id, state, profile_img);

				System.out.println(" ð  : " + regdate);

			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return request;
	}

	public Request getNextNotice(int id, boolean incHit) {
		Request notice = null;

		String sql = "select * from board" +
				"       where id = (" +
				"       select id from board" +
				"       where regdate > (select regdate from board where id = ?)" +
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
				String deadline = rs.getString("deadline");
				int price = rs.getInt("price");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int hit = rs.getInt("hit");
				String filename = rs.getString("filename");
				String how = rs.getString("how");
				String writer_id = rs.getString("writer_id");
				int state = rs.getInt("state");
				ArrayList list = getWriterInfo(writer_id);
				String profile_img = (String)list.get(0);
				String writer = (String)list.get(1);

				notice = new Request(nid, writer, title, kategorie, location, deadline, price, content, regdate, hit, filename, how, writer_id, state, profile_img);
				if(incHit) {
					stmt.execute("update board set hit=hit+1 where id=" + id);
				}


			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return notice;
	}

	public Request getPrevNotice(int id, boolean incHit) {
		Request notice = null;

		String sql =   "select * from (select * from board order by regdate desc) A " +
				" where regdate < (select regdate from board where id = ?) " +
				" order by id desc limit 1";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("id");

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
				ArrayList list = getWriterInfo(writer_id);
				String profile_img = (String)list.get(0);
				String writer = (String)list.get(1);

				notice = new Request(nid, writer, title, kategorie, location, deadline, price, content, regdate, hit, filename, how, writer_id, state, profile_img);
				if(incHit) {
					stmt.execute("update board set hit=hit+1 where id=" + id);
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

	public String getBoardWrtierName(int boardNum){

		String id = null;
		String name = null;
		System.out.println(boardNum);
		try {

			String sql = "SELECT id, writer_id FROM board where id=?";
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