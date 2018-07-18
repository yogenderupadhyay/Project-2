package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.backend.DAO.CategoryDAO;
import com.project.backend.domain.Category;

@Controller
public class CategoryController {

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private Category category;
	@Autowired 
	HttpSession httpSession;
	@RequestMapping(name = "/category/get/{id}", method = RequestMethod.GET)
	public ModelAndView getCategory(@RequestParam("id") String id) {
		category = categoryDAO.get(id);
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("category", category);
		return mv;
	}
	@RequestMapping("/category/save/")
	public ModelAndView saveCategory(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("description") String description) 
	{
		ModelAndView mv = new ModelAndView("redirect:/addcategories");
		category.setId(id);
		category.setName(name);
		category.setDescription(description);
		if(categoryDAO.save(category))
		{
			mv.addObject("CategorySuccessMessage", "The category created successfully");
    	}
		else
		{
			mv.addObject("categoryErrorMessage", "Could not able to create category.Please try after sometime.");
		}
		return mv;
	}
	@PutMapping("/category/update/")
	public ModelAndView updateCategory(@ModelAttribute Category category) {
		ModelAndView mv = new ModelAndView("redirect:/addcategories");
		if (categoryDAO.update(category) == true) {
			mv.addObject("CategorySuccessMessage", "The category updated successfully");
		} 
		else {
			mv.addObject("categorySuccessMessage", "Could not update the category.");
		}
		return mv;
	}
	@RequestMapping("/category/delete")
	public ModelAndView deleteCategory(@RequestParam String id) {
		System.out.println("going to delete category : " + id);
		ModelAndView mv = new ModelAndView("redirect:/viewcategories");
		if (categoryDAO.delete(id) == true) {
			mv.addObject("CategorySuccessMessage", "The category deleted successfully");
		} else {
			mv.addObject("CategoryErrorMessage", "Could not delete the category.");
		}
		return mv;
	}
	@GetMapping("/categories")
	public ModelAndView getAllCategories() {
		ModelAndView mv = new ModelAndView("index");
		System.out.println("1");
		List<Category> categories = categoryDAO.list();
		mv.addObject("categories", categories);
		return mv;
	}
	@RequestMapping("/category/edit")
	public ModelAndView editCategory(@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("redirect:/addcategories");
		category = categoryDAO.get(id);
		httpSession.setAttribute("selectedCategory", category);
		return mv;

		

	}



}

