package com.example.demo.mapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface BoardMapper {

	@Select("""
			SELECT
				id,
				title,
				writer,
				inserted
			FROM Board
			ORDER BY id DESC
			""")
	List<Board> selectAll();
	// 보통 게시물을 볼 때 최신거를 먼저 보니까
	
	@Select("""
			SELECT * 
			FROM Board
			WHERE id = #{id}
			""")
	Board selectById(Integer id);
	
	@Update("""
			UPDATE Board
			SET
				title = #{title},
				body = #{body},
				writer = #{writer}
			WHERE
				id = #{id}
			""")
	int update(Board board);
	
	@Delete("""
			DELETE FROM Board
			WHERE id = #{id}
			""")
	int deleteBtId(int id);
	
	@Insert("""
			INSERT INTO Board(title, body, writer)
			VALUES(#{title}, #{body}, #{writer})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Board board);
	
}
