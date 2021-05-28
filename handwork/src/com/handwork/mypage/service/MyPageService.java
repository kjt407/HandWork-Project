package com.handwork.mypage.service;

import com.handwork.dao.DAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

public class MyPageService {
    Connection conn;
    DAO dao;

    public MyPageService(){
        dao = new DAO();
        conn = dao.getConnection();
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
                    "UNION ALL " +
                    "SELECT id, content, regdate, title, boardname FROM free WHERE writer_id = ? " +
                    ") a order by regdate desc limit ?,5";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, writer_id);
            stmt.setString(2, writer_id);
            stmt.setString(3, writer_id);
            stmt.setInt(4, 5 * page - 5);

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

            if (page < getBoardPage(writer_id)) {
                result.put("next", true);
            } else {
                result.put("next", false);
            }
            System.out.println("result : "+result.toJSONString());
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
                    "UNION ALL " +
                    "SELECT id, content, regdate, title, boardname FROM free WHERE writer_id = ? " +
                    ") a order by regdate desc";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, writer_id);
            stmt.setString(2, writer_id);
            stmt.setString(3, writer_id);

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
                    "SELECT board_num, content, regdate, replyname, user_id  FROM review WHERE user_id = ? " +
                    "UNION ALL " +
                    "SELECT board_num, content, regdate, replyname, user_id  FROM comment WHERE user_id = ? " +
                    "UNION ALL " +
                    "SELECT board_num, content, regdate, replyname, user_id  FROM fcomment WHERE user_id = ? " +
                    ") a order by regdate  desc limit ?,5";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, writer_id);
            stmt.setString(2, writer_id);
            stmt.setString(3, writer_id);
            stmt.setInt(4, 5 * page - 5);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();

                int board_num = rs.getInt("board_num");
                String content = rs.getString("content");
                String regdate = rs.getString("regdate");
                String writer = rs.getString("user_id");
                String replyname = rs.getString("replyname");

                obj.put("board_num", board_num);
                obj.put("content", content);
                obj.put("regdate", regdate);
                obj.put("writer", writer);
                obj.put("replyname", replyname);


                System.out.println(obj);
                array.add(obj);
            }
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
                    "UNION ALL " +
                    "SELECT idx, content, regdate, replyname, user_id  FROM fcomment WHERE user_id = ? " +
                    ") a order by regdate  desc";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, writer_id);
            stmt.setString(2, writer_id);
            stmt.setString(3, writer_id);

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
    //이름 수정
    public JSONObject updateInfo(String id, HttpServletRequest request){

        JSONObject obj = new JSONObject();
        String sql = null;
        PreparedStatement stmt = null;
        String column = request.getParameter("col");
        String data = request.getParameter("data");
        System.out.println(id);
        System.out.println(column);
        System.out.println(data);



        try {
            sql = "update member set "+column+"=?  where id=?";
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, data);
            stmt.setString(2, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }



        return obj;
    }

    public JSONObject loadInfo(String id, HttpServletRequest request){
        JSONObject obj = new JSONObject();

        String sql = null;
        PreparedStatement stmt = null;

        try {
            sql = "select * from member where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String img = rs.getString("profile_img");
                obj.put("userName", name);
                obj.put("userEmail", email);
                obj.put("userPhone", phone);
                obj.put("userImg", img);

                HttpSession session = request.getSession();
                session.setAttribute("name", name);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return obj;
    }

    public JSONObject checkAccount(String id){
        JSONObject obj = new JSONObject();
        String sql = null;
        PreparedStatement stmt = null;
        String pw = null;
        try {
            sql = "select pw from member where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pw = rs.getString("pw");
            }

            if(pw!=null && pw.equals("null")){
                obj.put("result", false);
            }else{
                obj.put("result", true);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return obj;
    }

    public JSONObject checkPw(String id, String pw){
        JSONObject obj = new JSONObject();

        String sql = null;
        PreparedStatement stmt = null;
        String pw_=null;


        try {
            sql = "select pw from member where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               pw_ = rs.getString("pw");
            }
            if(pw.equals(pw_)){
                obj.put("result", true);
            }else{
                obj.put("result", false);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return obj;
    }

    public JSONObject editPw(String id, String pw){
        JSONObject obj = new JSONObject();

        String sql = null;
        PreparedStatement stmt = null;
        String pw_=null;


        try {
            sql = "update member set pw=?  where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pw);
            stmt.setString(2, id);
            stmt.executeUpdate();

            obj.put("result", true);

            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
            obj.put("result", false);
        }
        return obj;
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