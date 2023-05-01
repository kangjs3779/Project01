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
	
	@Select("""
			<script>
			<bind name="pattern" value="'%' + search + '%'" />
			SELECT
				id,
				title,
				writer,
				inserted
			FROM Board
			<where>
				<if test="(type == 'all') or (type == 'title')">
					title LIKE #{pattern}
				</if>
				<if test="(type == 'all') or (type == 'body')">
					OR body LIKE #{pattern}				
				</if>
				<if test="(type == 'all') or (type == 'writer')">
					OR writer LIKE #{pattern}				
				</if>
			</where>
			ORDER BY id DESC
			LIMIT #{startIndex}, #{rowPerPage}
			</script>
			""")
	List<Board> selectAllPaging(Integer startIndex, Integer rowPerPage, String search, String type);
	
	@Select("""
			<script>
			<bind name="pattern" value="'%' + search + '%'" />
			SELECT COUNT(*)
			FROM Board
			<where>
				<if test="(type == 'all') or (type == 'title')">
					title LIKE #{pattern}
				</if>
				<if test="(type == 'all') or (type == 'body')">
					OR body LIKE #{pattern}				
				</if>
				<if test="(type == 'all') or (type == 'writer')">
					OR writer LIKE #{pattern}				
				</if>
			</where>
			</script>
			""")
	Integer countAll(String search, String type);
	
}
