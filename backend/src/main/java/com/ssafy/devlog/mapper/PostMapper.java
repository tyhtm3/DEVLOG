package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.Post;

@Mapper
public interface PostMapper {
	
	public int selectPostCntByFeed(@Param("seq_user")int seq_user, @Param("disclosure")int disclosure,@Param("tag") List<String> tag);
	public List<Post> selectPostByFeed(@Param("seq_user") int seq_user,@Param("disclosure")int disclosure, @Param("offset") int offset, @Param("limit") int limit, @Param("tag") List<String> tag);
	
	public int selectPostCntByBlog(@Param("seq_user")int seq_user, @Param("seq_blog")int seq_blog,@Param("tag") List<String> tag);
	public List<Post> selectPostByBlog(@Param("seq_user") int seq_user,@Param("seq_blog")int seq_blog, @Param("offset") int offset, @Param("limit") int limit, @Param("tag") List<String> tag);
	
	public Post selectPost(int seq);
	public int insertPost(Post post);
	public int insertPostBasic(Post post);
	public int updatePost(Post post);
	public int updatePostBasic(Post post);
	public int deletePost(int seq);
	

}
