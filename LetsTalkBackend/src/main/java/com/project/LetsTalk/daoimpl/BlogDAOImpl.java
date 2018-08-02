package com.project.LetsTalk.daoimpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.LetsTalk.dao.BlogDAO;
import com.project.LetsTalk.model.Blog;
import com.project.LetsTalk.model.BlogComment;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	SessionFactory sessionFactory;

	private int getMaxBlogID() {
		int maxValue = 100;
		try {
			maxValue = (Integer) sessionFactory.getCurrentSession().createQuery("select max(blogId) from Blog")
					.uniqueResult();
			System.out.println("maxvalue " + maxValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		return maxValue;
	}
//*****************************add blog**************************************
	@Override
	public boolean addBlog(Blog blog) {
		try {
			blog.setBlogId(getMaxBlogID() + 1);
			blog.setDateCreated(new Date(System.currentTimeMillis()));
			blog.setStatus('N');
			sessionFactory.getCurrentSession().save(blog);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
//*****************************delete blog************************************
	@Override
	public boolean deleteBlog(int blogId) {
		try {
			sessionFactory.getCurrentSession().delete(getBlog(blogId));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}
//***************************update blog**************************************
	@Override
	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
//*************************get blog by blog id************************************
	@Override
	public Blog getBlog(int blogId) {
		// TODO Auto-generated method stub
		return (Blog) sessionFactory.getCurrentSession().createCriteria(Blog.class)
				.add(Restrictions.eq("blogId", blogId)).uniqueResult();
	}
//*************************get all blog of user********************************************
	@Override
	public List<Blog> listBlogOfUser(String emailId) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(Blog.class).add(Restrictions.eq("emailId", emailId))
				.list();
	}
//************************approve blog list*****************************************
	@Override
	public boolean approvedBlog(int blogId) {
		try {
			Blog blog = getBlog(blogId);
			blog.setStatus('A');
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
//**********************reject blog*****************************************************
	@Override
	public boolean rejectBlog(int blogId) {
		try {
			Blog blog = getBlog(blogId);
			blog.setStatus('R');
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
//*************************list of all blogd***************************************
	@Override
	public List<Blog> allBlogList() {
		return sessionFactory.getCurrentSession().createQuery("from Blog").list();
	}
//******************************approved blog list************************************
	@Override
	public List<Blog> approvedBlogList() {
		return sessionFactory.getCurrentSession().createQuery("from Blog where status='A'").list();
	}
//*********************rejected blog list******************************************
	@Override
	public List<Blog> rejectedBlogList() {
		return sessionFactory.getCurrentSession().createQuery("from Blog where status='R'").list();
	}
//**********************new blog list**************************************
	@Override
	public List<Blog> newBlogList() {
		return sessionFactory.getCurrentSession().createQuery("from Blog where status='N'").list();
	}
//**********************increment blog likes*****************************************
	@Override
	public boolean incLikes(int blogid) {
		try {
			Blog blog = getBlog(blogid);
			blog.setBlogLikes(blog.getBlogLikes() + 1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
//********************increment dislike blog******************************************
	@Override
	public boolean incDisLikes(int blogid) {
		try {
			Blog blog = getBlog(blogid);
			blog.setBlogDislikes(blog.getBlogDislikes() + 1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

//****************************** get max blogComment Id**************************************

	private int getMaxBlogCommentID() {
		int maxValue = 100;
		try {
			maxValue = (Integer) sessionFactory.getCurrentSession().createQuery("select max(blogCommentId) from BlogComment")
					.uniqueResult();
			System.out.println("maxvalue " + maxValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		return maxValue;
	}
//*******************************Count Comments of blog********************************
	public int countBlogComments(int blogId) {
		try {
			int max=(Integer) sessionFactory.getCurrentSession()
					.createQuery("select count(blogId) from BlogComment").uniqueResult();
			return max;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
//*******************************add blog comment**************************************
	@Override
	public boolean addBlogComment(BlogComment blogComment) {
		try {
			blogComment.setBlogCommmentId(getMaxBlogCommentID()+1);
			blogComment.setCommentDate(new Date());
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
//******************************delete blog comment**************************************
	@Override
	public boolean deleteBlogComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
//******************************get blog comment*******************************************
	@Override
	public BlogComment getBlogComment(int blogCommentId) {
		try {
			Session session = sessionFactory.openSession();
			BlogComment blogComment = session.get(BlogComment.class, blogCommentId);
			return blogComment;
		} catch (Exception e) {
			return null;
		}
	}
// **************************list all blog comment*********************************************
	@Override
	public List<BlogComment> listBlogComment(int blogId) {
		return sessionFactory.getCurrentSession().createCriteria(BlogComment.class)
				.add(Restrictions.eq("blogId", blogId)).list();
	}

}
