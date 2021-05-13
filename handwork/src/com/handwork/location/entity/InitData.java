package com.handwork.location.entity;

public class InitData {
    private String x;
    private String y;
    private String board_num;

    public InitData(String x, String y, String board_num) {
        this.x = x;
        this.y = y;
        this.board_num = board_num;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getBoard_num() {
        return board_num;
    }

    public void setBoard_num(String board_num) {
        this.board_num = board_num;
    }

    @Override
    public String toString() {
        return "InitData{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", board_num='" + board_num + '\'' +
                '}';
    }
}
