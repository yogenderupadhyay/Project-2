package com.project.middleware.restController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LetsTalk.dao.BlogDAO;
import com.project.LetsTalk.model.Blog;
import com.project.LetsTalk.model.BlogComment;

@RestController
public class BlogController {
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private Blog blog;
	
	@Autowired
	private BlogComment blogComment;
	
	@Autowired
	HttpSession session;
	
//------------------------------Add Blog -----------------------------------
	@PostMapping(value="/addBlog")
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog ){
		System.out.println("Inside insert blog");
		if (blogDAO.addBlog(blog)) {
			System.out.println("==========>blog added successfully..");
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		} else {
			return new ResponseEntity<Blog>(blog, HttpStatus.BAD_REQUEST);
		}
	}
	
//--------------------------------list all blog ------------------------------------------

		@GetMapping(value = "/listBlogs")
		public ResponseEntity<List<Blog>> listAllBlog() {
			List<Blog> listBlogs = blogDAO.allBlogList();
			System.out.println("==========> Blog details retrieved<==========");
			if (listBlogs.size() != 0) {
				return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.NOT_FOUND);
			}
		}
		
//-----------------------list all blog with same username ---------------------------------
				@GetMapping(value = "/listBlogsOfUser/{emailId}")
				public ResponseEntity<List<Blog>> listBlogOfUser(@PathVariable String emailId) {
					List<Blog> listBlogs = blogDAO.listBlogOfUser(emailId);
					System.out.println("==========> Blog details retrieved<==========");
					if (listBlogs.size() != 0) {
						return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.OK);
					} else {
						return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.NOT_FOUND);
					}
				}
//---------------------------Delete Blog------------------------------------
		@DeleteMapping(value="/deleteBlog/{blogID}")
		public ResponseEntity<Blog> deleteBlog(@PathVariable int blogID){
			Blog blog = blogDAO.getBlog(blogID);
			if(blog==null) {
				blog.setStatusMessage("NOT FOUND");
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			}
			blogDAO.deleteBlog(blogID);
			blog.setStatusMessage("OK");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			
		}
//----------------------------Update Blog -----------------------------------

		@PutMapping("updateBlog")
		public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
			if (blogDAO.getBlog(blog.getBlogId()) == null) {
				System.out.println("inside update blog ==null");
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			}
		//if user existed
			if(	blogDAO.updateBlog(blog) )
			  {
				System.out.println("inside update blog ==not null");
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			  }
			System.out.println("inside update blog ==error");
				return new ResponseEntity<Blog>(blog, HttpStatus.INTERNAL_SERVER_ERROR);
			}
//-----------------------------Get Blog ------------------------------------

		@GetMapping(value = "/getBlog/{blogId}")
		public ResponseEntity<Blog> getBlog(@PathVariable("blogId") int blogId) {
			System.out.println("Get Blog " + blogId);
			Blog blog = blogDAO.getBlog(blogId);
			if (blog == null) {
				System.out.println("Blog retrieval failure..");
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			} else {
				System.out.println("<==========Blog retrieved========>");
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
		}
//---------------------------Approve Blog ----------------------------------

		@PutMapping(value = "/approveBlog/{blogId}")
		public ResponseEntity<Blog> approveBlog(@PathVariable("blogId") int blogId) {
			System.out.println("Approve Blog with Blog ID: " + blogId);
			Blog blog = blogDAO.getBlog(blogId);
			
			if (blog == null) {
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			} 
			if(blog!=null) {
				blogDAO.approvedBlog(blogId);
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}else {
				return new ResponseEntity<Blog>(blog, HttpStatus.BAD_REQUEST);
			}
		}

//-----------------------------Reject Blog ----------------------------------

		@PutMapping(value = "/rejectBlog/{blogId}")
		public ResponseEntity<Blog> rejectBlog(@PathVariable("blogId") int blogId) {
			System.out.println("Reject Blog with Blog ID: " + blogId);
			Blog blog = blogDAO.getBlog(blogId);
			if (blog == null) {
				System.out.println("Not blog with blog Id: " + blogId + " found for Approval");
				return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
			} 
			if(blog!=null) {
				blog.setStatus('R');
				blogDAO.rejectBlog(blog.getBlogId());
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}else {
				return new ResponseEntity<Blog>(blog, HttpStatus.BAD_REQUEST);
			}
		}
	
//---------------------------list all approved blog ---------------------------------

				@GetMapping(value = "/listApprovedBlogs")
				public ResponseEntity<List<Blog>> listApprovedBlog() {
					List<Blog> listBlogs = blogDAO.approvedBlogList();
					if (listBlogs.size() != 0) {
						return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.OK);
					} else {
						return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.NOT_FOUND);
					}
				}
				
//----------------------------list all rejected blog ---------------------------------

				@GetMapping(value = "/listRejectedBlogs")
				public ResponseEntity<List<Blog>> listRejectedBlog() {
					List<Blog> listBlogs = blogDAO.rejectedBlogList();
					if (listBlogs.size() != 0) {
						return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.OK);
					} else {
						return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.NOT_FOUND);
					}
				}
//------------------------------list all New blog ---------------------------------

				@GetMapping(value = "/listNewBlogs")
				public ResponseEntity<List<Blog>> listNewBlog() {
					List<Blog> listBlogs = blogDAO.newBlogList();
					
					if(listBlogs.size()==0) {
						return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
					}
					if (listBlogs.size() != 0) {
						return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.OK);
					} else {
						return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.BAD_REQUEST);
					}
				}
//------------------------------Like blog -----------------------------------------
				@RequestMapping("likeBlog/{blogid}")
				public ResponseEntity<Blog> likeBlog(@PathVariable int blogid)
				{
					if(blogDAO.incLikes(blogid))
					{
						blog.setStatusMessage("OK");
						return new ResponseEntity<Blog>(blog, HttpStatus.OK);
					}
					else
					{
						blog.setStatusMessage("NOT_OK");
						return new ResponseEntity<Blog>(blog, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				
//-------------------------------Dislike blog -----------------------------------------
				@RequestMapping("dislikeBlog/{blogid}")
				public ResponseEntity<Blog> dislikeBlog(@PathVariable int blogid)
				{
					if(blogDAO.incDisLikes(blogid))
					{
						blog=blogDAO.getBlog(blogid);
						return new ResponseEntity<Blog>(blog, HttpStatus.OK);
					}
					else
					{
						blog.setStatusMessage("NOT_OK");
						return new ResponseEntity<Blog>(blog, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}	
//------------------------------------------Add BlogComments ---------------------------------------------------

				@PostMapping(value = "/addBlogComment")
				public ResponseEntity<BlogComment> addBlogComments(@RequestBody BlogComment blogComment) {
					
					if (blogDAO.addBlogComment(blogComment)) {
						return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
					} else {
						return new ResponseEntity<BlogComment>(blogComment, HttpStatus.NOT_FOUND);
					}
				}

  //--------------------------------------Delete BlogComment---------------------------------------------------

				@DeleteMapping(value = "/deleteBlogComment/{commentId}")
				public ResponseEntity<String> deleteBlogComments(@PathVariable("commentId") int commentId) {
					System.out.println("Delete blogComment with comment Id: " + commentId);
					BlogComment blogComment = blogDAO.getBlogComment(commentId);
					if (blogComment == null) {
						System.out.println("No blog " + commentId + " found to delete");
						return new ResponseEntity<String>("No blogcomment with comment Id: " + commentId + " found to delete",
								HttpStatus.NOT_FOUND);
					} else {
						blogDAO.deleteBlogComment(blogComment);
						return new ResponseEntity<String>("BlogComment with comment Id " + commentId + " deleted successfully", HttpStatus.OK);
					}

				}
//---------------------------------------Get BlogComment -------------------------------------------------------

				@GetMapping(value = "/getBlogComment/{commentId}")
				public ResponseEntity<BlogComment> getBlogComment(@PathVariable("commentId") int commentId) {
					System.out.println("Get BlogComment " + commentId);
					BlogComment blogComment = blogDAO.getBlogComment(commentId);
					if (blogComment == null) {
						return new ResponseEntity<BlogComment>(blogComment, HttpStatus.NOT_FOUND);
					} else {
						return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
					}
				}

//-----------------------------------list Blog comments ---------------------------------
				@GetMapping(value = "/listBlogComments/{blogID}")
				public ResponseEntity<List<BlogComment>> listBlogComments(@PathVariable int blogID) {
					List<BlogComment> listBlogComments = blogDAO.listBlogComment(blogID);
					System.out.println("******************************************************************************"+listBlogComments.size());
			/*		listBlogComments.size();*/
						return new ResponseEntity<List<BlogComment>>(listBlogComments, HttpStatus.OK);
					
				}
}
