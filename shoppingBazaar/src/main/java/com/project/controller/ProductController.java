package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.backend.DAO.CategoryDAO;
import com.project.backend.DAO.ProductDAO;
import com.project.backend.DAO.SupplierDAO;
import com.project.backend.domain.Product;
import com.project.util.FileUtil;


@Controller
public class ProductController {
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Product product;

	@Autowired
	HttpSession httpSession;

	@Autowired
	FileUtil fileUtil;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/productget/{product.id}")
	public ModelAndView getSelectedProduct(@PathVariable("product.id") String id, RedirectAttributes redirectAttributes) {
		log.info("Search started");
		ModelAndView mv = new ModelAndView("index");
		 product =  productDAO.get(id);
		mv.addObject("selectedProducts",  product);
		mv.addObject("isUserSelectedProduct",  true);
		mv.addObject("getProduct", true);
		return mv;
	}

	@RequestMapping("/product/save/")
	public ModelAndView saveProduct(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") String price,
			@RequestParam("categoryID") String categoryID, @RequestParam("supplierID") String supplierID,
			@RequestParam("file") MultipartFile file) {
		ModelAndView mv = new ModelAndView("redirect:/addproducts");
		log.info("Saving Product");
		mv.addObject("productSuccessMessage", "The product created successfully");
		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		price = price.replace(",", "");
		product.setPrice(Integer.parseInt(price));
		product.setCategoryId(categoryID);
		product.setSupplierId(supplierID);
		if (productDAO.save(product)) {
			
			if (FileUtil.copyFileNIO(file, product.getId() + ".PNG")) {
				mv.addObject("uploadMessage", "product image successfully updated");
			} else {
				mv.addObject("uploadMessage", "Coulod not upload image");
			}
		} else {
			mv.addObject("productErrorMessage", "Coulc not able to create product.  please contact admin");
		}
		
		return mv;
	}

	@PutMapping("/product/update/")
	public ModelAndView updateProduct(@ModelAttribute Product product) {
		ModelAndView mv = new ModelAndView("index");
		if (productDAO.update(product) == true) {
			mv.addObject("successMessage", "The product updated successfully");
		} else {
			mv.addObject("errorMessage", "Could not update the product.");
		}
		return mv;
	}

	@GetMapping("/product/delete")
	public ModelAndView deleteProduct(@RequestParam String id) {
		System.out.println("going to delete product : " + id);
		ModelAndView mv = new ModelAndView("redirect:/viewproducts");
		log.info("deleting Product");
		if (productDAO.delete(id) == true) {
			mv.addObject("successMessage", "The product deleted successfully");
			log.info("product deleted");
		} else {
			mv.addObject("errorMessage", "Could not delete the product.");
		}
		return mv;
	}

	@GetMapping("/product/edit")
	public ModelAndView editProduct(@RequestParam String id) {
		ModelAndView mv = new ModelAndView("redirect:/addproducts");
		product = productDAO.get(id);
		httpSession.setAttribute("selectedProduct", product);
		return mv;
	}

	@GetMapping("/products")
	public ModelAndView getAllCategories() {
		ModelAndView mv = new ModelAndView("index");
		List<Product> products = productDAO.list();
		mv.addObject("products", products);
		return mv;
	}
	@GetMapping("search")
	public ModelAndView searchProduct(@RequestParam("searchString") String searchString)
	{
		ModelAndView mv = new ModelAndView("index");
		log.info("Search started");
		List<Product> products =  productDAO.search(searchString);
		mv.addObject("selectedProducts", products);
		mv.addObject("isUserSelectedProduct", true);
		mv.addObject("searchProducts", true);
		if(products.size()<=0) {
			mv.addObject("searchError", "Product not found try again with valid characters");
		}
		log.info("Number of products with search string " +searchString +  " is/are : " + products.size());
		return mv;
	}
}
