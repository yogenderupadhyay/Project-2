package com.project.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.backend.DAO.CartDAO;
import com.project.backend.DAO.UserDAO;
import com.project.backend.domain.Cart;
import com.project.backend.domain.User;

@Controller
public class UserController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private User user;
	@Autowired
	private Cart cart;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	HttpSession httpSession;
	@Autowired
	HttpServletResponse httpServletResponse;
	@Autowired
	HttpServletRequest httpServletRequest;
	boolean register_Success;

	@PostMapping("/validate")
	public ModelAndView validate(@RequestParam("uname") String username, @RequestParam("psw") String password, @RequestParam ("remember") String rem)

	{
		ModelAndView mv = new ModelAndView("index");
		user = userDAO.validate(username, password);
		if (user == null) {
			mv.addObject("errorMessage", "Invalid credentials..please try again.");

		} else {
			if(rem=="on"){
				Cookie ck =new Cookie(username, password);
				httpServletResponse.addCookie(ck);
			} 
			httpSession.setAttribute("welcomeMessage", user.getName());
			httpSession.setAttribute("loggedInUserID", user.getEmailID());
			httpSession.setAttribute("isLoggedIn", true);
			List<Cart> carts = cartDAO.list(user.getEmailID(),'N');
			List<Cart> orders = cartDAO.list(user.getEmailID(),'O');
			httpSession.setAttribute("size", carts.size());
			httpSession.setAttribute("carts", carts);
			if (user.getRole() == 'A')

			{
				httpSession.setAttribute("isAdmin", true);
			}
		}
		return mv;

	}

	@RequestMapping("/logout")
	public ModelAndView logout() {

		ModelAndView mv = new ModelAndView("index");
		/* mv.addObject("isUserClickedLogin", true); */
		mv.addObject("welcomeMessage", null);
		httpSession.invalidate();
		mv.addObject("logoutMessage", "Successfully logout");
		return mv;

	}
	@RequestMapping("/register")
	public ModelAndView register(@RequestParam("fName") String fName, 
			@RequestParam("LName") String LName, 
			@RequestParam("uname") String username, 
			@RequestParam("psw") String password,
			@RequestParam("gender") String gender,
			@RequestParam("mobile") String mobile,
			@RequestParam("dob") String dOB) {

		ModelAndView mv = new ModelAndView("redirect:/userHome");
		user.setName(fName);
		user.setLastName(LName);
		user.setEmailID(username);
		user.setPassword(password);
		user.setdOB(dOB);
		user.setMobile(mobile);
		user.setGender(gender);
		if(userDAO.save(user)) {
			
			mv.addObject("registrationSuccessMessage", user.getName()+"Successfully Registered");
			register_Success =true;
    	}
		else
		{
			mv.addObject("registrationErrorMessage", "try after some time or please contact admin");
		}
		return mv;
	}
	@RequestMapping("/userHome")
	public ModelAndView loginUserHome()
	{
	 ModelAndView mv=new ModelAndView("index");
	 try {
		if (user.getRole() == 'A')

			{
				httpSession.setAttribute("isAdmin", true);
				httpSession.setAttribute("isUserSelectedProduct",  false);
				mv.addObject("isUserClickedHome", false);
			}
		 if (register_Success==true)

			{
			 httpSession.setAttribute("welcomeMessage", user.getName());
				httpSession.setAttribute("loggedInUserID", user.getEmailID());
				httpSession.setAttribute("isLoggedIn", true);
				List<Cart> carts = cartDAO.list(user.getEmailID(),'N');
				httpSession.setAttribute("size", carts.size());
				httpSession.setAttribute("carts", carts);
			}
	} catch (NullPointerException e) {
		// TODO Auto-generated catch block
		
	}
	 return mv;
	}
}

