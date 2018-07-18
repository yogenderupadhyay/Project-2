package com.project.LetsTalk.dao;

import java.util.List;

import com.project.LetsTalk.model.Blog;
import com.project.LetsTalk.model.BlogComment;

public interface BlogDAO {
	public boolean addBlog(Blog blog);
	public boolean deleteBlog(int blog);
	public boolean updateBlog(Blog blog);
	public Blog getBlog(int blogId);
	public List<Blog> listBlogOfUser(String emailId);
	public boolean approvedBlog(int BlogId);
	public boolean rejectBlog(int BlogId);
	public List<Blog> allBlogList();
	public List<Blog> approvedBlogList();
	public List<Blog> rejectedBlogList();
	public List<Blog> newBlogList();
	public boolean incLikes(int BlogId);
	public boolean incDisLikes(int BlogId);
	
	/*****************************blog comment **************************************/
	public boolean addBlogComment(BlogComment blogComment);
    public boolean deleteBlogComment(BlogComment BlogComment);
    public BlogComment getBlogComment(int blogCommentId);
    public List<BlogComment> listBlogComment(int blogId);
}
