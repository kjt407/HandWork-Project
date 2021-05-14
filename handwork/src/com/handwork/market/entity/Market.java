package com.handwork.market.entity;

public class Market {

    private int id;
    private String writer;
    private String title;
    private String kategorie;
    private String location;
    private int period;
    private int price;
    private String content;
    private String regdate;
    private int hit;
    private String filename;
    private String how;
    private Boolean isUpdate;
    private String writer_id;
    private int state;
    private int count;
    private int starAvg;
    private String topReview;

    public Market() {
        super();
    }

    public Market(String writer, String title, String kategorie, String location, int period, int price,
                  String content, String regdate, int hit, String filename, String how, String writer_id, int state) {
        super();

        this.writer = writer;
        this.title = title;
        this.kategorie = kategorie;
        this.location = location;
        this.period = period;
        this.price = price;
        this.content = content;
        this.regdate = regdate;
        this.hit = hit;
        this.filename = filename;
        this.how = how;
        this.writer_id = writer_id;
        this.state = state;
    }

    public Market(int id, String writer, String title, String kategorie, String location, int period, int price,
                  String content, String regdate, int hit, String filename, String how, String writer_id, int state) {
        super();
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.kategorie = kategorie;
        this.location = location;
        this.period = period;
        this.price = price;
        this.content = content;
        this.regdate = regdate;
        this.hit = hit;
        this.filename = filename;
        this.how = how;
        this.writer_id = writer_id;
        this.state = state;
    }

    public Market(int id, String writer, String title, String kategorie, String location, int period, int price,
                  String content, String regdate, int hit, String filename, String how, String writer_id, int state, int count, int starAvg, String topReview) {
        super();
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.kategorie = kategorie;
        this.location = location;
        this.period = period;
        this.price = price;
        this.content = content;
        this.regdate = regdate;
        this.hit = hit;
        this.filename = filename;
        this.how = how;
        this.writer_id = writer_id;
        this.state = state;
        this.count = count;
        this.starAvg = starAvg;
        this.topReview = topReview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStarAvg() {
        return starAvg;
    }

    public void setStarAvg(int starAvg) {
        this.starAvg = starAvg;
    }

    public String getTopReview() {
        return topReview;
    }

    public void setTopReview(String topReview) {
        this.topReview = topReview;
    }


    @Override
    public String toString() {
        return "Request [writer=" + writer + ", title=" + title + ", kategorie=" + kategorie + ", location=" + location
                + ", period=" + period + ", price=" + price + ", content=" + content + ", regdate=" + regdate
                + ", hit=" + hit + "]";
    }




}
