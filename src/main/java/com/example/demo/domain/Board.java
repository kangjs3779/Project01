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
}







