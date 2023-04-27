package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;
import com.example.demo.service.*;

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
	public String list(Model model) { // 경로가 두개니까 두개의 jsp를 만들어야 하나? 귀찮으니까 void말고 string타입으로 forward하겠음
		// 1. request param 수집/가공
		// 2. business logic 처리
//		List<Board> list = mapper.selectAll();
		List<Board> list = service.listBoard();
		// 3. add attribute
		model.addAttribute("boardList", list);

		// 여기까지 잘 됨
		// 4. forward/redirect
		return "list";
		// 자기 자신으로 포워드 됨 -> 그래서 두번 출력이 된 것을 확인 할 수 있다
	}

	@GetMapping("/id/{id}")
	public String board(@PathVariable("id") Integer id, Model model) {
		// 1.request param
		// 2. business logic
		Board board = service.getBoard(id);
		// 3. add attribute
		model.addAttribute("board", board);
		System.out.println(board);
		// 4. forward/redirect
		return "get";
	}

	@GetMapping("/modify/{id}")
	public String modify(@PathVariable("id") Integer id, Model model) {
		// 조회하려는 애임

		model.addAttribute("board", service.getBoard(id));
		return "modify";
	}

//	@RequestMapping(value = "/modify/{id}", method=RequestMethod=POST)
	@PostMapping("/modify/{id}")
	public String modifyProcess(Board board, RedirectAttributes rttr) {
//		System.out.println(board);
		boolean ok = service.modify(board);

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
	public void addForm() {
		// 게시물 작성 form (view)로 포워드

	}

	@PostMapping("add")
	public String addProcess(Board board, RedirectAttributes rt) {
		// 새 게시물 db에 추가
		// 파라미터에 String title...이렇게 적어도 되는데 자바빈에 프로퍼티 다 있으니까
		boolean ok = service.addBoard(board);
		if (ok) {
			rt.addFlashAttribute("message", board.getTitle() + " 게시글이 추가가 되었습니다");
			return "redirect:/id/" + board.getId();
		} else {
			rt.addFlashAttribute("board", board);
			return "redirect:/add";
		}

	}

}
