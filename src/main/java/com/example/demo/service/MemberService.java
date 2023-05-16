package com.example.demo.service;



import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardLikeMapper likeMapper;

	public boolean signup(Member member) {

		//암호화 
		String plain = member.getPassword();
		member.setPassword(passwordEncoder.encode(plain));
		// encode해주는 빈이 필요함
		int count = mapper.insert(member);
		return 1 == count;
	}

	public List<Member> listMember() {
		List<Member> memberList = mapper.selectAllMember();

		return memberList;
	}

	public Member get(String id) {
		Member member = mapper.selectById(id);
		return member;
	}

	public boolean remove(Member member) {
		Member oldMember = mapper.selectById(member.getId());
		int count = 0;
		if (passwordEncoder.matches(member.getPassword(), oldMember.getPassword())) {
			// 암호가 같으면??
			
			// 이 회원이 작성한 게시물 row 삭제
			boardService.removeByWriter(member.getId());
			
			// 이 회원이 좋아요한 레코드 삭제
			likeMapper.deleteByMemberId(member.getId());
			
			//회원 테이블 삭제
			count = mapper.deleteById(member);

		} else {
			// 암호가 같지 않으면??

		}
		return count == 1;
	}

	public boolean modify(Member member, String oldPassword) {
		//패스워드를 바꾸기 위해 입력했다면....
		if(!member.getPassword().isBlank()) {
			// 입력된 패스워드를 암호화..
			String plain = member.getPassword();
			member.setPassword(passwordEncoder.encode(plain));
		}
		Member oldMember = mapper.selectById(member.getId());

		int count = 0;
		if (passwordEncoder.matches(oldPassword, oldMember.getPassword())) {
			//기존 암호와 같으면 업데이트 하는 것이다 
			count = mapper.update(member);
		}

		return count == 1;
	}

	public Map<String, Object> checkId(String id) {
		Member member = mapper.selectById(id);
		
		return Map.of("available", member == null);
	}

	public Map<String, Object> checkNickName(String nickName, Authentication authentication) {
		Member member = mapper.selectByNickName(nickName);
		if(authentication != null) {
			Member oldMember = mapper.selectById(authentication.getName());
			
			return Map.of("available", member == null || oldMember.getNickName().equals(nickName));
		} else {
			return Map.of("available", member == null);
		}
		
	}

	public Map<String, Object> checkEmail(String email) {
		Member member = mapper.selectByEmail(email);
		return Map.of("available", member == null);
	}

	

	
}
