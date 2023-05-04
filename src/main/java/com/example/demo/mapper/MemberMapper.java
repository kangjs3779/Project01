package com.example.demo.mapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface MemberMapper {
	
	@Insert("""
			INSERT INTO Member (id, password, nickName, email)
			VALUES (#{id}, #{password}, #{nickName}, #{email})
			""")
	int insert(Member member);
	
	@Select("""
			SELECT 
				id,
				password,
				nickName,
				email,
				inserted
			FROM
				Member
			ORDER BY inserted DESC
			""")
	List<Member> selectAllMember();
	
	@Select("""
			SELECT
				id,
				password,
				nickName,
				email,
				inserted
			FROM
				Member
			WHERE id = #{id}
			""")
	Member selectById(String id);

}
