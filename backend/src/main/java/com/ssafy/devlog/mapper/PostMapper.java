package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;

import com.ssafy.devlog.dto.Post;

@Mapper
public interface PostMapper {
	
	public List<Post> selectPostByFeed(@Param("seq_user") int seq_user,@Param("disclosure")int disclosure, @Param("offset") int offset, @Param("limit") int limit, @Param("tag") List<String> tag);
	public List<Post> selectPostByBlog(@Param("seq_user") int seq_user,@Param("seq_blog")int seq_blog, @Param("offset") int offset, @Param("limit") int limit, @Param("tag") List<String> tag);
	public int selectPostCntByBlog(@Param("seq_user") int seq_user,@Param("seq_blog")int seq_blog);
	
	public Post selectPost(int seq);
	public List<Post> selectDraftPost(int seq_blog);
	public String selectPostTitle(int seq);
	public int insertPost(Post post);
	public int insertPostBasic(Post post);
	public int updatePost(Post post);
	public int updatePostBasic(Post post);
	public int deletePost(int seq);
	

}
