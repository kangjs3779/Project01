package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Data
public class Board {
	private int id;
	private String title;
	private String body;
	private LocalDateTime inserted;
	private String writer;
	private Boolean liked;
	private List<String> fileName;
	private Integer fileCount;
	private Integer likeCount;
	
	
	
	
}







