package com.example.demo.domain;

import java.time.*;

import lombok.*;

@Data
public class Board {
	private int id;
	private String title;
	private String body;
	private LocalDateTime inserted;
	private String writer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public LocalDateTime getInserted() {
		return inserted;
	}
	public void setInserted(LocalDateTime inserted) {
		this.inserted = inserted;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", body=" + body + ", inserted=" + inserted + ", writer="
				+ writer + "]";
	}
	
}







