package com.handwork.comment.entity;

public class Comments {
    private int idx;
    private String user_id;
    private String content;
    private String regdate;
    private int board_num;
    private int c_state;
    private String name;
    private int price;

    public Comments() {
        super();
    }

    public Comments(int idx, String user_id, String content, String regdate, int board_num, int c_state, String name, int price) {
        super();
        this.idx = idx;
        this.user_id = user_id;
        this.content = content;
        this.regdate = regdate;
        this.board_num = board_num;
        this.c_state = c_state;
        this.name = name;
        this.price = price;
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

    public void setRegdate(String c_time) {
        this.regdate = c_time;
    }

    public int getBoard_num() {
        return board_num;
    }

    public void setBoard_num(int board_num) {
        this.board_num = board_num;
    }

    public int getC_state() {
        return c_state;
    }

    public void setC_state(int c_state) {
        this.c_state = c_state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "comment [idx=" + idx + ", user_id=" + user_id + ", content=" + content + ", regdate=" + regdate
                + ", board_num=" + board_num + "]";
    }

}
