package com.handwork.review.service;


import com.handwork.review.entity.Reviews;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {



    public List<Reviews> getReviewsList(int board_num) {

        List<Reviews> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";

            String sql="SELECT * FROM review where board_num=? order by idx desc";

            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, board_num);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                int idx = rs.getInt("idx");
                String user_id = rs.getString("user_id");
                String content = rs.getString("content");
                String r_time = rs.getString("r_time");
                int board_num_ = rs.getInt("board_num");
                String name = rs.getString("name");
                int star = rs.getInt("star");

                Reviews reviews = new Reviews(idx, user_id, content, r_time, board_num_, name, star);
                list.add(reviews);

            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }



}
