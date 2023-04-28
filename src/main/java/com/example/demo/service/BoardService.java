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

	public Map<String, Object> listBoard(Integer page, String search) {
		// 페이지 당 행의 수
		Integer rowPerPage = 10;
		Integer startIndex = (page -1) * rowPerPage;
		// 게시물 목록
		
		//페이지네이션이 필요한 정보
		//전체 레코드의 개수
		Integer numOfRecords = mapper.countAll(search);
		// 마지막 페이지 번호
		Integer lastPageNumber = (numOfRecords - 1) / rowPerPage + 1;
		
		//페이지네이션 왼쪽번호
		Integer leftPageNum = page - 5;
		// 1보다 작을 수 없음
		leftPageNum = Math.max(leftPageNum, 1);
		
		//페이지 네이션 오른쪽 번호
		Integer rightPageNum = leftPageNum + 9;
		// lastPageNum보다 클 수 없음
		rightPageNum = Math.min(rightPageNum, lastPageNumber);
		
		//값을 저장해서 컨트롤러에 보내야 하는데 자바빈에 담아도 되고 map에 만들어도 된다
		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("rightPageNum", rightPageNum);
		pageInfo.put("leftPageNum", leftPageNum);
		pageInfo.put("currentPageNum", page);
		pageInfo.put("lastPageNum", lastPageNumber);
//		pageInfo.put("lastPageNum", lastPageNumber);
		
		List<Board> list = mapper.selectAllPaging(startIndex, rowPerPage, search);
		return Map.of("pageInfo", pageInfo,
					  "boardList", list);
		// 페이지네이션이 필요한 정보
	}
	
}
