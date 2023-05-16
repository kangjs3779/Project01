package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;
import com.example.demo.service.*;

import lombok.*;

@Controller
@RequestMapping("/") // 이건 왜 씀???
public class BoardController {

	@Autowired
	private BoardService service;
	// 의미있는 이름으로 지을 것, null이면 안되니까 꼭 injection받을거니까 autowired 어노테이션을 써준다

	// 게시물 목록
	// 경로 : http://localhost:8080 or http://localhost:8080/list
	// @RequestMapping({"/", "list"}, method = RequestMethod.GET)
	@GetMapping({ "/", "list" })
	public String list(
			Model model, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "type", required = false) String type) { // 경로가 두개니까 두개의 jsp를 만들어야 하나? 귀찮으니까 void말고 string타입으로 forward하겠음
		// 1. request param 수집/가공
		// 2. business logic 처리
//		List<Board> list = mapper.selectAll();
		//List<Board> list = service.listBoard(); 페이지 처리 전
		Map<String, Object> result = service.listBoard(page, search, type); //페이지 철; 흐
		// 3. add attribute
//		model.addAttribute("boardList", result.get("boardList"));
//		model.addAttribute("pageInfo", result.get("pageInfo"));
		model.addAllAttributes(result);

		System.out.println(result);
		
		// 여기까지 잘 됨
		// 4. forward/redirect
		return "list";
		// 자기 자신으로 포워드 됨 -> 그래서 두번 출력이 된 것을 확인 할 수 있다
	}

	@GetMapping("/id/{id}")
	public String board(
			@PathVariable("id") Integer id, 
			Model model,
			Authentication authentication) {
		// 1.request param
		// 2. business logic
		Board board = service.getBoard(id, authentication);
		System.out.println(board);
		// 3. add attribute
		model.addAttribute("board", board);
		System.out.println(board);
		// 4. forward/redirect
		return "get";
	}

	@GetMapping("/modify/{id}")
	@PreAuthorize("isAuthenticated() and @customSecurityChecker.checkBoardWriter(authentication, #id)")
	public String modify(@PathVariable("id") Integer id, Model model) {
		// 조회하려는 애임

		model.addAttribute("board", service.getBoard(id));
		return "modify";
	}

//	@RequestMapping(value = "/modify/{id}", method=RequestMethod=POST)
	@PostMapping("/modify/{id}")
	//본인이 작성한 글만 수정이 가능함(게시글의 작성자와 로그인시 토큰에 저장된 작성자가 같은지 확인)
	@PreAuthorize("isAuthenticated() and @customSecurityChecker.checkBoardWriter(authentication, #board.id)")
	public String modifyProcess(
			Board board, 
			RedirectAttributes rttr,
			@RequestParam(value = "files", required = false) MultipartFile[] addFiles,
			@RequestParam(value = "removeFiles", required = false) List<String> removeFileNames) throws Exception {

		boolean ok = service.modify(board, removeFileNames, addFiles);

		if (ok) {
			// 리디렉트할 때 특정값을 같이 보내도록함
			rttr.addFlashAttribute("message", board.getId() + "번 게시글을 수정하였습니다");
			return "redirect:/id/" + board.getId();
			// 수정하고 나서 해당 게시물 보기로 리다이렉션
//			return "redirect:/" + board // 수정한 내용을 보도록 하는것

		} else {
			// 리디렉트할 때 특정값을 같이 보내도록함, 쿼리스트링으로 붙음
			rttr.addAttribute("fail", "fail");

			return "redirect:/modify/" + board.getId();
			// 수정 폼으로 리디렉션
			// 이런일이 있으면 이렇게 추가하면 된다는 코드임
		}
	}

	@PostMapping("remove")
	@PreAuthorize("isAuthenticated() and @customSecurityChecker.checkBoardWriter(authentication, #id)")
	public String remove(int id, RedirectAttributes rttr) {
		boolean ok = service.remove(id);
		if (ok) {
			rttr.addFlashAttribute("message", id + "번 게시물이 삭제되었습니다"); 
			return "redirect:/list";
		} else {
			return "redirect:/id/" + id;
		}
	}

	@GetMapping("add")
	@PreAuthorize("isAuthenticated()")
	public void addForm() {
		// 게시물 작성 form (view)로 포워드

	}

	@PostMapping("add")
	@PreAuthorize("isAuthenticated()")
	public String addProcess(
			Board board, 
			RedirectAttributes rt,
			@RequestParam("files") MultipartFile[] files,
			Authentication authentication) throws Exception {
		// 새 게시물 db에 추가
		// 파라미터에 String title...이렇게 적어도 되는데 자바빈에 프로퍼티 다 있으니까
		board.setWriter(authentication.getName());
		boolean ok = service.addBoard(board, files);
		if (ok) {
			rt.addFlashAttribute("message", board.getTitle() + " 게시글이 추가가 되었습니다");
			return "redirect:/id/" + board.getId();
		} else {
			rt.addFlashAttribute("board", board);
			return "redirect:/add";
		}

	}

	@PostMapping("/like")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> like(
			@RequestBody Like like,
			Authentication authentication) {
		System.out.println(authentication);
		
		if(authentication == null) {
			//로그인이 안되어 있으면
			return ResponseEntity
					.status(403)
					.body(Map.of("message", "로그인 후 좋아요 클릭 해주세요."));
					//응답 정보에 403을 주고 map타입의 값을 응답본문에 붙여서 반환
					//403 : 서버가 요청을 이해했지만 권한이 없어서 접근이 불가능한 것을 말한다
		} else {
			//로그인이 되어 있으면
			return ResponseEntity
					.ok()
					.body(service.like(like, authentication));
					//응답 본문에 like메소드의 결과를 반환
					//service에서 만들었던 map타입의 result변수가 body의 파라미터로 들어감
					//응답 본문에 이 map타입 정보가 붙어서 감
		}
		
	}
}
