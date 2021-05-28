package com.handwork.free.entity;

public class Free {

	private int id;
	private String writer;
	private String title;
	private String content;
	private String regdate;
	private int hit;
	private String writer_id;
	private int count;
	private String profile_img;


	public Free(int id, String writer, String title, String content, String regdate, int hit, String writer_id, int count) {
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.writer_id = writer_id;
		this.profile_img = profile_img;
		this.count = count;
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


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	@Override
	public String toString() {
		return "Free{" +
				"id=" + id +
				", writer='" + writer + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", regdate='" + regdate + '\'' +
				", hit=" + hit +
				", writer_id='" + writer_id + '\'' +
				", profile_img='" + profile_img + '\'' +
				'}';
	}
}