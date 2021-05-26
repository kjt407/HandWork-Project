package com.handwork.free.service;

import com.handwork.free.entity.Free;
import com.handwork.request.entity.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FreeService {

	Connection conn;

	public FreeService(){
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

	public List<Free> getFreeList() {
		return getFreeList("title", "", 1);
	}

	public List<Free> getFreeList(int page) {
		return getFreeList("title", "", page);
	}

	public List<Free> getFreeList(String field, String query, int page) {

		List<Free> list = new ArrayList<>();

		String sql="SELECT *" +
				"FROM( " +
				"SELECT @rownum:=@rownum+1  num, A.* FROM free A, " +
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

				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int hit = rs.getInt("hit");
				String filename = rs.getString("filename");
				String writer_id = rs.getString("writer_id");

				Free free = new Free(id, writer, title, content, regdate, hit, filename, writer_id, getCount(id));

				list.add(free);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int getFreeCount() {
		return getFreeCount("title", "");
	}

	public int getFreeCount(String field, String query) {

		int count = 0;
		String sql="SELECT count(id) count " +
				"FROM( " +
				"SELECT @ROWNUM:=@ROWNUM+1  num, A.* FROM free A, " +
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

	public Free getFree(int id) {
		Free free = null;
		String sql = "select * from free where id=?";


		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int hit = rs.getInt("hit");
				String filename = rs.getString("filename");
				String writer_id = rs.getString("writer_id");
				ArrayList list = getWriterInfo(writer_id);
				String profile_img = (String)list.get(0);
				String writer = (String)list.get(1);


				free = new Free(nid, writer, title, content, regdate, hit, filename, writer_id, getCount(id));

			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return free;
	}

	public Free getNextFree(int id, boolean incHit) {
		Free free = null;

		String sql = "select * from free" +
				"       where id = (" +
				"       select id from free" +
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
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int hit = rs.getInt("hit");
				String filename = rs.getString("filename");
				String writer_id = rs.getString("writer_id");
				ArrayList list = getWriterInfo(writer_id);
				String profile_img = (String)list.get(0);
				String writer = (String)list.get(1);

				free = new Free(nid, writer, title, content, regdate, hit, filename, writer_id, getCount(id));
				if(incHit) {
					stmt.execute("update board set hit=hit+1 where id=" + id);
				}


			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return free;
	}

	public Free getPrevFree(int id, boolean incHit) {
		Free free = null;

		String sql =   "select * from (select * from free order by regdate desc) A " +
				" where regdate < (select regdate from free where id = ?) " +
				" limit 1";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("id");

				String title = rs.getString("title");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int hit = rs.getInt("hit");
				String filename = rs.getString("filename");
				String writer_id = rs.getString("writer_id");
				ArrayList list = getWriterInfo(writer_id);
				String profile_img = (String)list.get(0);
				String writer = (String)list.get(1);

				free = new Free(nid, writer, title, content, regdate, hit, filename, writer_id, getCount(id));
				if(incHit) {
					stmt.execute("update board set hit=hit+1 where id=" + id);
				}


			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return free;
	}





	public int getCount(int boardNum){
		System.out.println("getCount 메서드 실행");
		int count =0;
		try {

			String sql = "SELECT count(board_num) count from freecomment where board_num=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, boardNum);

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {

		}
		return 0;
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

	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}