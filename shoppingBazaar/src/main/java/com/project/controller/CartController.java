package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.backend.DAO.CartDAO;
import com.project.backend.DAO.ProductDAO;
import com.project.backend.DAO.UserDAO;
import com.project.backend.domain.Cart;
import com.project.backend.domain.Product;
import com.project.backend.domain.User;

@Controller
public class CartController {
	Logger log = LoggerFactory.getLogger(CartController.class);
	@Autowired
	private CartDAO cartDAO;
	@Autowired  
	private Cart cart;
	@Autowired
	private ProductDAO productDAO;
	@Autowired 
	private Product product;
	@Autowired
	private User user;
	@Autowired 
	private UserDAO userDAO;
	@Autowired 
	private HttpSession httpSession;
	
	@GetMapping("/checkout")
	public ModelAndView order()
	{
		ModelAndView mv = new ModelAndView("index");
		String loggedInUserID =(String) httpSession.getAttribute("loggedInUserID");
		if (cartDAO.update(loggedInUserID))
		{
			mv.addObject("successMessage", "Your order placed successfully...");
		}
		else
		{
			mv.addObject("errorMessage", "Your order could placed.   please try after some time.");
		}
		
		return mv;
	}
	@GetMapping("/cartadd/{productID}")
	public ModelAndView addToCart(
			@PathVariable("productID") String productID)
	{
		ModelAndView mv = new ModelAndView("redirect:/mycart");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID=="null" || loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to add any product to cart");
			return mv;
		}
		product=productDAO.get(productID);
		 List<Cart> cartList = cartDAO.list(loggedInUserID,'N');
		for(Cart c:cartList) {
			
			if(c.getProductID().equals(productID)) {
				int q=c.getQuantity()+1;
				cartDAO.update(productID,q );
				mv.addObject("isUserSelectedProduct", true);
				mv.addObject("successMessage", "The product add to cart successfully");
				return mv;
			}
		}
			
		cart.setEmailID(loggedInUserID);
		cart.setPrice(product.getPrice());
		cart.setProductID(productID);
		cart.setProductName(product.getName());
		cart.setQuantity(1);
		cart.setStatus('N');
		cart.setId();
		if(cartDAO.save(cart))
		{
			mv.addObject("successMessage", "The product add to cart successfully");
			mv.addObject("isUserClickedMyCart", true);
		}
		else
		{
			mv.addObject("cartUnableToLoadError", "Could not add the product to cart..please try after some time");
			mv.addObject("isUserSelectedProduct", true);
		}
		return mv;
	}
	@RequestMapping("/mycart")
	public ModelAndView  getMyCartDetails()
	{
		log.debug("Starting of the method getMyCartDetails");
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isUserClickedMyCart", true);
		String loggedInUserID = (String)httpSession.getAttribute("loggedInUserID");
		log.info("Logged in user id : " + loggedInUserID);
		if(loggedInUserID==null)
		{
		  mv.addObject("errorMessage", "Please login to see your cart details");
		  return mv;
		}
		 List<Cart> cartList = cartDAO.list(loggedInUserID,'N');
		 mv.addObject("cartList", cartList);
		 log.debug("not of products in cart " + cartList.size());
		 log.debug("Ending of the method getMyCartDetails");
		 return mv;
	}
	@RequestMapping("/remove/{cart.id}")
	public ModelAndView removeCart(@PathVariable("cart.id") int ID) {
		ModelAndView mv = new ModelAndView("redirect:/mycart");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID=="null" || loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to add any product to cart");
			return mv;
		}
		if(cartDAO.delete(ID)) {
			System.out.println("The cart removed successfully");
			mv.addObject("CartSuccessMessage", "The cart removed successfully");
		} else {
			System.out.println("Could not remove the cart try again.");
			mv.addObject("CartErrorMessage", "Could not remove the cart try again.");
		}
		return mv;
	}
	/*@PostMapping("/payment")
	public ModelAndView paymentCart(@RequestParam("payment") String payment) {
		ModelAndView mv = new ModelAndView("redirect:/mycart");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID=="null" || loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to add any product to cart");
			return mv;
		}
		user=userDAO.get(loggedInUserID);
		cart=cartDAO.get(cart.getId());
		order.setUser(user);
		order.setCart(cart);
		order.setPayment(payment);
		if(orderDAO.save(order)) {
			System.out.println("The cart removed successfully");
			mv.addObject("orderSuccessMessage", "The order placed successfully");
		} else {
			System.out.println("Could not remove the cart try again.");
			mv.addObject("orderErrorMessage", "Could not place order try again.");
		}
		return mv;
	}*/
}

