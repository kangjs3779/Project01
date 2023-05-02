package com.example.demo.service;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;

//어차피 @component가 포함되어 있다
//특별한 component어노테이션이다 service역할을 하고 있는 component인 것이디
@Service 
@Transactional(rollbackFor = Exception.class)
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	public List<Board> listBoard(Integer page, String search) {
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

	public boolean addBoard(Board board, MultipartFile[] files) throws Exception {
		
		int count = mapper.insert(board);

		for(MultipartFile file : files) {
			//파일이 있을 때만 저장을 해야 하니까
			if(file.getSize() > 0) {
				System.out.println(file.getOriginalFilename());
				System.out.println(file.getSize());
				// 파일 저장(파일 시스템에 저장)
				String folder = "C:\\study\\upload\\" + board.getId();
				File targetFolder = new File(folder);
				if(!targetFolder.exists()) {
					targetFolder.mkdirs();
					//게시글의 기본키로 파일을 만드는 것
				}
				String path = folder + "\\" + file.getOriginalFilename();
				File target = new File(path);
				file.transferTo(target);
				//db에 관련 정보 저장(insert)
				mapper.insertFileName(board.getId(), file.getOriginalFilename());
				
				//이름은 같은데 내용은 다른 파일이 전송되면 덮어쓸 수 있으니까
				//게시물마다 폴더를 만들어서 그 폴더안에 저장하도록 한다
				//게시물 폴더도 이름이 겹치면 안되니까 이름은 primary키가 할 수 있을 것이다
				//파일이 안올라가도 게시글이 추가되는 것, 파일이 올라가야 게시글이 추가되는 것
				//어떤 것을 트랜잭션으로 할 것인지는 내가 선택할 문제이다
				//1. 폴더 만들기 = 게시물 번호로
				//2.트랜잭션 처리 하기
				
			}
		}
		
		//게시물 insert
		return count == 1;
	}

	public Map<String, Object> listBoard(Integer page, String search, String type) {
		// 페이지 당 행의 수
		Integer rowPerPage = 10;
		Integer startIndex = (page -1) * rowPerPage;
		// 게시물 목록
		
		//페이지네이션이 필요한 정보
		//전체 레코드의 개수
		Integer numOfRecords = mapper.countAll(search, type);
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
		
		List<Board> list = mapper.selectAllPaging(startIndex, rowPerPage, search, type);
		return Map.of("pageInfo", pageInfo,
					  "boardList", list);
		// 페이지네이션이 필요한 정보
	}
	
}
