package com.handwork.review.service;


import com.handwork.dao.DAO;
import com.handwork.review.entity.Reviews;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {
    Connection conn;
    DAO dao;

    public ReviewService(){
        dao = new DAO();
        conn = dao.getConnection();
    }

    public List<Reviews> getReviewsList(int board_num) {

        List<Reviews> list = new ArrayList<>();

        try {

            String sql="SELECT * FROM review where board_num=? order by idx desc";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, board_num);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                int idx = rs.getInt("idx");
                String user_id = rs.getString("user_id");
                String content = rs.getString("content");
                String regdate = rs.getString("regdate");
                int board_num_ = rs.getInt("board_num");

                int star = rs.getInt("star");
                ArrayList lists = getWriterInfo(user_id);
                String profile_img = (String)lists.get(0);
                String name = (String)lists.get(1);

                Reviews reviews = new Reviews(idx, user_id, content, regdate, board_num_, name, star, profile_img);
                list.add(reviews);

            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
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
