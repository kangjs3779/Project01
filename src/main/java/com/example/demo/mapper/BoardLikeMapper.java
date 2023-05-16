package com.example.demo.mapper;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface BoardLikeMapper {
	@Insert("""
			INSERT INTO BoardLike
			VALUES (#{boardId}, #{memberId})
			""")
	Integer insert(Like like);
	//like 자바빈안에 있는 게시글 번호와 회원아이디를 레코드로 추가한다

	@Delete("""
			DELETE FROM BoardLike
			WHERE boardId = #{boardId} 
			  AND memberId = #{memberId} 
			""")
	Integer delete(Like like);
	//like자바빈안에 있는 게시글 번호와 회원아이디를 통해서 
	//해당 게시글의 좋아요 정보를 지운다
	
	@Select("""
			SELECT COUNT(*) FROM BoardLike
			WHERE boardId = #{boardId}
			""")
	Integer countByBoardId(Integer boardId);
	//게시글번호를 이용해서 해당 게시글의 좋아요가 몇개인지 조회하는 쿼리
	
	@Select("""
			SELECT *
			FROM BoardLike
			WHERE boardId = #{boardId}
			AND memberId = #{memberId}
			""")
	Like select(Integer boardId, String memberId);
	
	@Delete("""
			DELETE FROM BoardLike
			WHERE boardId = #{boardId}
			""")
	void deleteByBoardId(Integer boardId);
	
	@Delete("""
			DELETE FROM BoardLike
			WHERE memberId = #{memberId}
			""")
	void deleteByMemberId(String memberId);
}
