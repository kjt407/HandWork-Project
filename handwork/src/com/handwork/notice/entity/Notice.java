package com.handwork.notice.entity;

public class Notice {

	private int id;
	private String category;
	private String writer;
	private String title;
	private String content;
	private String regdate;
	private String writer_id;

	public Notice() {
		super();
	}

	public Notice(int id, String category, String writer, String title, String content, String regdate, String writer_id) {
		this.id = id;
		this.category = category;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.writer_id = writer_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}


	@Override
	public String toString() {
		return super.toString();
	}
}