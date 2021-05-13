package com.handwork.location.controller;


import com.handwork.location.service.LocationService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/location")
public class LocationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-json; charset=UTF-8");
        String type = (request.getParameter("type"));
        JSONArray json = null;
        if(type != null && type.equals("init")){
            json = initMarker(request,response);
            response.getWriter().print(json);
        } else if(type == null) {
            request.getRequestDispatcher("/html/location/location.jsp").forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  super.doPost(request, response);

    }

    private JSONArray initMarker(HttpServletRequest request, HttpServletResponse response){
        LocationService service = new LocationService();

//        JSONArray jsonarray = new JSONArray();
//
//        JSONObject json = new JSONObject();
//        json.put("x","37.49099729728258");
//        json.put("y","126.81345371145218");
//        json.put("board_num","9");

        return service.getInitData();
    }










    private void registerDB(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("inputDB메서드 실행");
        String sessionID = req.getParameter("sessionID");
        int boardNum = Integer.parseInt(req.getParameter("boardNum"));
        int price = Integer.parseInt(req.getParameter("price"));
        String subs = req.getParameter("subs");
        String name = null;

        JSONArray jsonarray = new JSONArray();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";
            String sql = "select name from member where id = ?;";
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";

            String sql ="insert into comment(user_id,content,c_time,board_num,c_state,name,price) values(?,?,?,?,0,?,?)";
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
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

    private void loadDB(HttpServletRequest req, HttpServletResponse res) throws IOException {
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

            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";

            String sql="SELECT * FROM comment where board_num=? order by idx asc";

            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, boardNum);

            ResultSet rs = stmt.executeQuery();


            while(rs.next()){

                int idx = rs.getInt("idx");
                String user_id = rs.getString("user_id");
                String content = rs.getString("content");
                String c_time = rs.getString("c_time");

                int board_num_ = rs.getInt("board_num");
                int price_ = rs.getInt("price");
                int c_state = rs.getInt("c_state");
                String name_ = rs.getString("name");

                JSONObject jsonOb = new JSONObject();
                jsonOb.put("idx", idx);
                jsonOb.put("userid", user_id);
                jsonOb.put("name", name_);
                jsonOb.put("content", content);
                jsonOb.put("board_num", board_num_);
                jsonOb.put("c_state", c_state);
                jsonOb.put("time", c_time);
                jsonOb.put("price", price_);
                jsonarray.add(jsonOb);

            }
            System.out.println("loadDB 메서드 Try 끝나는 부분");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        writer.print(jsonarray);
    }

    private void deleteDB(HttpServletRequest req, HttpServletResponse res){
        System.out.println("deleteDB메서드 실행");

        int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";

            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            Statement stmt = conn.createStatement();


            stmt.executeUpdate(String.format("delete from comment where idx=" + commentIndex));

            stmt.close();
            conn.close();

            loadDB(req,res);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private void updateDB(HttpServletRequest req, HttpServletResponse res){
        System.out.println("update 메서드 실행");
        int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
        int price = Integer.parseInt(req.getParameter("price"));
        String subs = req.getParameter("subs");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";

            String sql ="update comment set price=? , content=? where idx=?";
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
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

    private void adoptDB(HttpServletRequest req, HttpServletResponse res){
        System.out.println("adopt 메서드 실행");
        int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
        int boardNum = Integer.parseInt(req.getParameter("boardNum"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";

            String sql ="update comment set c_state=1 where idx=?";
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,commentIndex);

            stmt.executeUpdate();

            sql = "update board set state=1 where id=?";
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
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

    private int getState(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("getState메서드 실행");
        int state =2;
        try {

            int boardNum = Integer.parseInt(req.getParameter("boardNum"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";
            String sql = "select state from board where id = ?;";
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
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



    private String getCurrentTime() {
        return LocalDate.now() + " " + LocalTime.now().toString().substring(0, 8);
    }
}
