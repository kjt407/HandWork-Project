package com.handwork.review.entity;

public class Reviews {
    private int idx;
    private String user_id;
    private String content;
    private String regdate;
    private int board_num;
    private String name;
    private int star;
    private String profile_img;


    public Reviews() {
        super();
    }

    public Reviews(int idx, String user_id, String content, String regdate, int board_num, String name, int star, String profile_img) {
        super();
        this.idx = idx;
        this.user_id = user_id;
        this.content = content;
        this.regdate = regdate;
        this.board_num = board_num;
        this.name = name;
        this.star = star;
        this.profile_img = profile_img;

    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public int getBoard_num() {
        return board_num;
    }

    public void setBoard_num(int board_num) {
        this.board_num = board_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    @Override
    public String toString() {
        return "comment [idx=" + idx + ", user_id=" + user_id + ", content=" + content + ", regdate=" + regdate
                + ", board_num=" + board_num + "]";
    }

}
