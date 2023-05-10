package com.example.demo.security;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	//이름만 userdetailsService이지 실제로 그런것은 아니기 때문에 implements를 해줘야 한다
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = mapper.selectById(username);
		//username이 있으면 파라미터로 넣어서 잘 확인하면 되고 
		//username이 null이면 usernameNotFoundException을 만들어서 던져주면 된다
		System.out.println("**********************로그인 하기위한 코드");
		System.out.println(member);
		if(member == null) {
			throw new UsernameNotFoundException(username + " 회원이 없습니다.");
		}
		
		//33번 라인 풀어쓰기
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		
		for(String auth : member.getAuthority()) {
			authorityList.add(new SimpleGrantedAuthority(auth));
		}
		
		UserDetails user = User.builder()
				.username(member.getId())
				.password(member.getPassword())
//				.authorities(member.getAuthority().stream().map(SimpleGrantedAuthority::new).toList())
				.authorities(authorityList) //스트림으로 써도 되고 이렇게 풀어서 써도 된다 
				.build();
		
		
		return user;
	}
}
