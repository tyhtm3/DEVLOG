package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.Post;

@Mapper
public interface PostMapper {
	
	public List<Post> selectAllPost(int seq_user);
	public List<Post> selectAllPostByNeighbor(int seq_user);
	public List<Post> selectAllPostByTag(@Param("seq_user") int seq_user, @Param("tag") List<String> tag);
	
	public List<Post> selectAllPostByBlog(@Param("seq_user") int seq_user, @Param("seq_blog") int seq_blog);
	public List<Post> selectAllPostByBlogByTag(@Param("seq_user") int seq_user, @Param("seq_blog") int seq_blog, @Param("tag") List<String> tag);
	
	public Post selectPost(int seq);
	public int insertPost(Post post);
	public int insertPostBasic(Post post);
	public int updatePost(Post post);
	public int updatePostBasic(Post post);
	public int deletePost(int seq);
	

}
