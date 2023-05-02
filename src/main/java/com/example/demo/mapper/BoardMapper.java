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
			SELECT 
				b.id,
				b.title,
				b.body,
				b.inserted,
				b.writer,
				f.fileName
			FROM Board b LEFT JOIN FileNames f ON b.id = f.boardId
			WHERE b.id = #{id}
			""")
	@ResultMap("boardResultMap")
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
				b.id,
				b.title,
				b.writer,
				b.inserted,
				COUNT(f.id) fileCount
			FROM Board b LEFT JOIN FileNames f
							ON b.id = f. boardId
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
			GROUP BY b.id
			ORDER BY b.id DESC
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
	
	@Insert("""
			INSERT INTO FileNames
				(boardId, fileName)
			VALUES
				(#{boardId}, #{originalFilename})
			""")
	Integer insertFileName(int boardId, String originalFilename);
	
}
