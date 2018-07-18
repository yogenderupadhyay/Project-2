package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.backend.DAO.CategoryDAO;
import com.project.backend.DAO.ProductDAO;
import com.project.backend.DAO.SupplierDAO;
import com.project.backend.domain.Category;
import com.project.backend.domain.Product;
import com.project.backend.domain.Supplier;
import com.project.backend.domain.User;

@Controller
public class AdminController {
	private static Logger log = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private SupplierDAO supplierDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private Category category;
	@Autowired
	private Supplier supplier;
	@Autowired
	private Product product;
	@Autowired
	private User user;
	@Autowired
	HttpSession httpSession;

	@GetMapping("/viewcategories")
	public ModelAndView admincClickedViewCategories() {
		ModelAndView mv = new ModelAndView("index");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");

		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login to do this operation");
			return mv;
		}
		Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");
		if (isAdmin == null || isAdmin == false) {
			mv.addObject("AuthorizationErrorMessage", "You are not autheroized to do this operations.");
			return mv;
		}
		log.debug("starting of the method admincClickedViewCategories");
		mv.addObject("isAdminClickeViewCategories", true);
		List<Category> categories = categoryDAO.list();
		httpSession.setAttribute("categories", categories);
		log.debug("ending of the method admincClickedCategories");
		return mv;
	}

	@GetMapping("/addcategories")
	public ModelAndView admincClickedCategories() {
		ModelAndView mv = new ModelAndView("index");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");

		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login to do this operation");
			return mv;
		}
		Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");
		if (isAdmin == null || isAdmin == false) {
			mv.addObject("AuthorizationErrorMessage", "You are not autheroized to do this operations.");
			return mv;
		}
		log.debug("starting of the method admincClickedAddCategories");
		mv.addObject("isAdminClickedAddCategories", true);
		log.debug("ending of the method admincClickedAddCategories");
		return mv;
	}

	@GetMapping("/viewproducts")
	public ModelAndView admincClickedViewProducts() {
		log.debug("starting of the method admincClickedViewProducts");
		ModelAndView mv = new ModelAndView("index");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");

		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login to do this operation");
			return mv;
		}
		Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");
		if (isAdmin == null || isAdmin == false) {
			mv.addObject("AuthorizationErrorMessage", "You are not autheroized to do this operations.");
			return mv;
		}
		mv.addObject("isAdminClickedViewProducts", true);
		List<Category> categories = categoryDAO.list();
		List<Supplier> suppliers = supplierDAO.list();
		List<Product> products = productDAO.list();
		httpSession.setAttribute("categories", categories);
		httpSession.setAttribute("suppliers", suppliers);
		httpSession.setAttribute("products", products);
		log.debug("ending of the method admincClickedProducts");
		return mv;
	}

	@GetMapping("/addproducts")
	public ModelAndView admincClickedAddProducts() {
		log.debug("starting of the method admincClickedAddProducts");
		ModelAndView mv = new ModelAndView("index");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");

		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login to do this operation");
			return mv;
		}
		Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");
		if (isAdmin == null || isAdmin == false) {
			mv.addObject("AuthorizationErrorMessage", "You are not autheroized to do this operations.");
			return mv;
		}
		mv.addObject("isAdminClickedAddProducts", true);
		log.debug("ending of the method admincClickedProducts");
		return mv;
	}

	@GetMapping("/viewsuppliers")
	public ModelAndView admincClickedViewSupplier() {
		log.debug("starting of the method admincClickedViewSupplier");
		ModelAndView mv = new ModelAndView("index");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");

		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login to do this operation");
			return mv;
		}
		Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");
		if (isAdmin == null || isAdmin == false) {
			mv.addObject("AuthorizationErrorMessage", "You are not autheroized to do this operations.");
			return mv;
		}
		mv.addObject("isAdminClickedViewSuppliers", true);
		List<Supplier> supplier = supplierDAO.list();
		httpSession.setAttribute("supplier", supplier);
		log.debug("ending of the method admincClickedSupplier");
		return mv;
	}

	@GetMapping("/addsuppliers")
	public ModelAndView admincClickedAddSupplier() {
		log.debug("starting of the method admincClickedViewSupplier");
		ModelAndView mv = new ModelAndView("index");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");

		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login to do this operation");
			return mv;
		}
		Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");
		if (isAdmin == null || isAdmin == false) {
			mv.addObject("AuthorizationErrorMessage", "You are not autheroized to do this operations.");
			return mv;
		}
		mv.addObject("isAdminClickedAddSuppliers", true);
		log.debug("ending of the method admincClickedSupplier");
		return mv;
	}
}

