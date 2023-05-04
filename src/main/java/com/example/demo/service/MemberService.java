package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	public boolean signup(Member member) {
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
}
