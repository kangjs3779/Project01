package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.mapper.MemberMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private BoardService boardService;

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
}
