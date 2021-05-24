package com.handwork.mypage.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class MyPageService {
    Connection conn;

    public MyPageService() {
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
    //로그인한 아이디가 작성한 게식글 가져오기
    public JSONObject getBoardList(String writer_id, int page) {

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();

        String sql = null;
        PreparedStatement stmt = null;

        try {
            sql = "select * from (" +
                    "SELECT id, content, regdate, title, boardname FROM board WHERE writer_id = ? " +
                    "UNION ALL " +
                    "SELECT id, content, regdate, title, boardname FROM market WHERE writer_id = ? " +
                    ") a order by regdate desc limit ?,5";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, writer_id);
            stmt.setString(2, writer_id);
            stmt.setInt(3, 5 * page - 5);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                int boardnum = rs.getInt("id");
                String boardname = rs.getString("boardname");
                String title = rs.getString("title");
                String regdate = rs.getString("regdate");

                obj.put("boardnum", boardnum);
                obj.put("boardname", boardname);
                obj.put("title", title);
                obj.put("regdate", regdate);

                array.add(obj);
            }
            result.put("list", array);
            System.out.println(result.toJSONString());

            result.put("list", array);
            if (page < getBoardPage(writer_id)) {
                result.put("next", true);
            } else {
                result.put("next", false);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

    public int getBoardPage(String writer_id) {
        int maxPage = 0;
        try {
            PreparedStatement stmt = null;

            String sql = "select count(*) from (" +
                    "SELECT id, content, regdate, title, boardname FROM board WHERE writer_id = ? " +
                    "UNION ALL " +
                    "SELECT id, content, regdate, title, boardname FROM market WHERE writer_id = ? " +
                    ") a order by regdate desc";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, writer_id);
            stmt.setString(2, writer_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int page = rs.getInt("count(*)");
                maxPage = (int) Math.ceil((double) page / 5);
            }
        } catch (Exception e) {
        }
        return maxPage;
    }

    //로그인한 아이디가 작성한 댓글 가져오기
    public JSONObject getReplyList(String writer_id, int page) {

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();

        String sql = null;
        PreparedStatement stmt = null;

        try {
            sql = "select * from (" +
                    "SELECT idx, content, regdate, replyname, user_id  FROM review WHERE user_id = ? " +
                    "UNION ALL " +
                    "SELECT idx, content, regdate, replyname, user_id  FROM comment WHERE user_id = ? " +
                    ") a order by regdate  desc limit ?,5";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, writer_id);
            stmt.setString(2, writer_id);
            stmt.setInt(3, 5 * page - 5);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();

                int idx = rs.getInt("idx");
                String content = rs.getString("content");
                String regdate = rs.getString("regdate");
                String writer = rs.getString("user_id");
                String replyname = rs.getString("replyname");

                obj.put("idx", idx);
                obj.put("content", content);
                obj.put("regdate", regdate);
                obj.put("writer", writer);
                obj.put("replyname", replyname);


                System.out.println(obj);
                array.add(obj);
            }
            result.put("list", array);


            result.put("list", array);
            if (page < getReplyPage(writer_id)) {
                result.put("next", true);
            } else {
                result.put("next", false);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(result.toJSONString());
        return result;
    }

    public int getReplyPage(String writer_id) {
        int maxPage = 0;
        try {
            PreparedStatement stmt = null;

            String sql = "select count(*) from (" +
                    "SELECT idx, content, regdate, replyname, user_id  FROM review WHERE user_id = ? " +
                    "UNION ALL " +
                    "SELECT idx, content, regdate, replyname, user_id  FROM comment WHERE user_id = ? " +
                    ") a order by regdate  desc";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, writer_id);
            stmt.setString(2, writer_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int page = rs.getInt("count(*)");
                maxPage = (int) Math.ceil((double) page / 5);
            }
        } catch (Exception e) {
        }
        return maxPage;
    }

    //회원정보수정


    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}