package com.handwork.comment.service;


public class CommentService {
/*
    public List<Comment> getCommentList() {
        return getCommentList("title", "", 1);
    }

    public List<Comment> getCommentList(int board_num) {
        return getCommentList("title", "", board_num);
    }*/

   /* public List getCommentList(int board_num) {

        List list = new ArrayList();



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";

            String sql="SELECT * FROM comment where board_num=? order by idx desc";

            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, board_num);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                int idx = rs.getInt("idx");
                String user_id = rs.getString("user_id");
                String content = rs.getString("content");
                String c_time = rs.getString("c_time");

                int board_num_ = rs.getInt("board_num");

                int c_state = rs.getInt("c_state");
                String name = rs.getString("name");


                Comments comment = new Comments(idx, user_id, content, c_time, board_num_, c_state, name, price);
                String a = comment.getUser_id();
                String b = comment.getName();
                String c = comment.getContent();
                String d = comment.getC_time();
                int e = comment.getC_state();
                list.add(a);
                list.add(b);
                list.add(c);
                list.add(d);
                list.add(e);

            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
*/
}
