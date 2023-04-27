package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;

@Service //특별한 component어노테이션이다 service역할을 하고 있는 component인 것이디
//어차피 @component가 포함되어 있다
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	public List<Board> listBoard() {
		List<Board> list =  mapper.selectAll();
		return list;
		// 컨트롤러에게 준다
		// 컨트롤러가 서비스에게 일을 시키고 서비스는 매퍼에게 주고
		// 매페는 서비스에게 주고 서비스는 컨트롤러에게 준다
		// 큰 프로젝트는 이렇게 한다
	}

	public Board getBoard(Integer id) {
		return mapper.selectById(id);
	}

	public boolean modify(Board board) {
		int count = mapper.update(board);
		
		return count == 1;
	}

	public boolean remove(int id) {
		int count = mapper.deleteBtId(id);
		return count == 1;
	}

	public boolean addBoard(Board board) {
		int count = mapper.insert(board);
		return count == 1;
	}
	
}
