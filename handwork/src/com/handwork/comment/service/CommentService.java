package com.handwork.comment.service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CommentService {

    Connection conn;

    public CommentService(){
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

    public void registerDB(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("inputDB메서드 실행");
        String sessionID = req.getParameter("sessionID");
        int boardNum = Integer.parseInt(req.getParameter("boardNum"));
        int price = Integer.parseInt(req.getParameter("price"));
        String subs = req.getParameter("subs");
        String name = null;

        JSONArray jsonarray = new JSONArray();


        try {
            String sql = "select name from member where id = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,sessionID);


            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                name = rs.getString("name");
                System.out.println(name);
            }
        }catch (Exception e){

        }
        try {

            String sql ="insert into comment(user_id,content,regdate,board_num,c_state,name,price) values(?,?,?,?,0,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1,sessionID);
            stmt.setString(2,subs);
            stmt.setString(3,getCurrentTime());
            stmt.setInt(4,boardNum);
            stmt.setString(5,name);
            stmt.setInt(6,price);

            stmt.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
        }
        loadDB(req,res);
    }

    public void loadDB(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("loadDB 메서드 처음");
        PrintWriter writer = res.getWriter();
        System.out.println("loadDB 메서드 writter null 할당부분");
        System.out.println("bordnum: "+req.getParameter("boardNum"));
        int boardNum = Integer.parseInt(req.getParameter("boardNum"));
        System.out.println("loadDB 메서드 boardnum 할당부분");


        JSONArray jsonarray = new JSONArray();
        System.out.println("loadDB 메서드 Try  진입전");
        try {
            System.out.println("loadDB 메서드 writter 할당 부분");
            String sql="SELECT * FROM comment where board_num=? order by idx asc";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, boardNum);

            ResultSet rs = stmt.executeQuery();


            while(rs.next()){

                int idx = rs.getInt("idx");
                String user_id = rs.getString("user_id");
                String content = rs.getString("content");
                String regdate = rs.getString("regdate");

                int board_num_ = rs.getInt("board_num");
                int price_ = rs.getInt("price");
                int c_state = rs.getInt("c_state");
                ArrayList list = getWriterInfo(user_id);
                String profile_img = (String)list.get(0);
                String writer_ = (String)list.get(1);

                JSONObject jsonOb = new JSONObject();
                jsonOb.put("idx", idx);
                jsonOb.put("userid", user_id);
                jsonOb.put("name", writer_);
                jsonOb.put("content", content);
                jsonOb.put("board_num", board_num_);
                jsonOb.put("c_state", c_state);
                jsonOb.put("time", regdate);
                jsonOb.put("price", price_);
                jsonOb.put("profile_img", profile_img);
                jsonarray.add(jsonOb);

            }
            System.out.println("loadDB 메서드 Try 끝나는 부분");

            rs.close();
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        writer.print(jsonarray);
    }

    public void deleteDB(HttpServletRequest req, HttpServletResponse res){
        System.out.println("deleteDB메서드 실행");

        int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));

        try {

            Statement stmt = conn.createStatement();


            stmt.executeUpdate(String.format("delete from comment where idx=" + commentIndex));

            stmt.close();

            loadDB(req,res);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void updateDB(HttpServletRequest req, HttpServletResponse res){
        System.out.println("update 메서드 실행");
        int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
        int price = Integer.parseInt(req.getParameter("price"));
        String subs = req.getParameter("subs");

        try {
            String sql ="update comment set price=? , content=? where idx=?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,price);
            stmt.setString(2,subs);
            stmt.setInt(3,commentIndex);

            stmt.executeUpdate();
            loadDB(req,res);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void adoptDB(HttpServletRequest req, HttpServletResponse res){
        System.out.println("adopt 메서드 실행");
        int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
        int boardNum = Integer.parseInt(req.getParameter("boardNum"));

        try {

            String sql ="update comment set c_state=1 where idx=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,commentIndex);

            stmt.executeUpdate();

            sql = "update board set state=1 where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,boardNum);
            stmt.executeUpdate();

            JSONObject json = new JSONObject();
            json.put("state",2);
            json.put("boardNum",boardNum);


            res.getWriter().print(json);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public int getState(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("getState메서드 실행");
        int state =2;
        try {

            int boardNum = Integer.parseInt(req.getParameter("boardNum"));
            String sql = "select state from board where id = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, boardNum);


            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                state = rs.getInt("state");
                System.out.println("게시글 state 값 : "+state);

            }

        } catch (Exception e) {

        }
        return state;
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



    private String getCurrentTime() {
        return LocalDate.now() + " " + LocalTime.now().toString().substring(0, 8);
    }

    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
