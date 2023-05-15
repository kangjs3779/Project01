package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.service.*;

import jakarta.servlet.http.*;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("checkId/{id}")
	@ResponseBody
	public Map<String, Object> checkId(
			@PathVariable("id") String id) {
		
		return service.checkId(id);
	}
	
	@GetMapping("checkNickName/{nickName}")
	@ResponseBody
	public Map<String, Object> checkNickName(
			@PathVariable("nickName") String nickName,
			Authentication authentication) {
		return service.checkNickName(nickName, authentication);
	}
	
	@GetMapping("checkEmail/{email}")
	@ResponseBody
	public Map<String, Object> checkEmail(@PathVariable("email") String email) {
		return service.checkEmail(email);
	}
	
	@GetMapping("login")
	public void loginForm() {
		//forward
	}
	
	@GetMapping("signup")
	@PreAuthorize("isAnonymous()")
	public void signupForm() {
	}
	
	@PostMapping("signup")
	@PreAuthorize("isAnonymous()")
	public String signupProcess(Member member, RedirectAttributes rttr) {
		try {
			service.signup(member);
			rttr.addFlashAttribute("message", "회원가입이 되었습니다.");

			return "redirect:/list";
			
		} catch(Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("member", member);
			rttr.addFlashAttribute("message", "회원가입 중 문제가 발생하였습니다.");
			return "redirect:/member/signup";
		}
	}
	
	@GetMapping("list")
	@PreAuthorize("hasAuthority('admin')")
	public void list(Model model) {
		List<Member> memberList = service.listMember();
		model.addAttribute("members", memberList);
	}
	
	// 경로 : /member/info?id=asdf
	@GetMapping("info")
	// 회원 정보는 본인 것만 볼 수 있도록
	@PreAuthorize("hasAuthority('admin') or (isAuthenticated() and (authentication.name eq #id))")
	public void info(String id, Model model) {
		Member member = service.get(id);
		model.addAttribute("member", member);
	}
	
	@PostMapping("remove")
	@PreAuthorize("isAuthenticated() and (authentication.name eq #member.id)")
	public String remove(Member member, 
			RedirectAttributes rttr,
			HttpServletRequest request) throws Exception {
		boolean ok = service.remove(member);
		if (ok) {
			rttr.addAttribute("message", "회원을 탈퇴하였습니다.");
			
			// 회원 탈퇴 후 로그아웃
			request.logout();
			
			return "redirect:/list";
		} else {
			rttr.addAttribute("message", "회원 탈퇴 시 문제가 발생하였습니다.");
			return "redirect:/member/info?id=" + member.getId();
		}
	}
	
	@GetMapping("modify")
	@PreAuthorize("isAuthenticated() and (authentication.name eq #id)")
	public void modifyForm(String id, Model model) {
		//view로 포워드 
		Member member = service.get(id);
		model.addAttribute("member", member);
		//어차피 자바빈이 모델에 붙을 때 따로 이름을 명시하지 않으면
		//lowerCamelCase로 붙게 되니까 코드를 줄일 수 있다 
//		model.addAttribute(service.get(id));
	}
	
	@PostMapping("modify")
	@PreAuthorize("isAuthenticated() and (authentication.name eq #member.id)")
	public String modifyProcess(Member member, 
			RedirectAttributes rttr,
			String oldPassword) {
		boolean ok = service.modify(member, oldPassword);
		
		if(ok) {
			rttr.addAttribute("message", "수정이 잘 되었습니다.");
			return "redirect:/member/list";
		} else {
			rttr.addAttribute("message", "수정하지 못했습니다.");
			return "redirect:/member/modify?id=" + member.getId();
		}
	}
	

}








