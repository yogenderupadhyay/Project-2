package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.backend.DAO.SupplierDAO;
import com.project.backend.domain.Supplier;

@Controller
public class SupplierController {
	@Autowired
	private SupplierDAO supplierDAO;
	@Autowired
	private Supplier supplier;
	@Autowired
	HttpSession httpSession;
	@RequestMapping("/Supplier/get/{id}")
	public ModelAndView getSupplier(@RequestParam("id") String id) {
		supplier = supplierDAO.get(id);
		ModelAndView mv = new ModelAndView("redirect:/addsuppliers");
		mv.addObject("supplier", supplier);
		return mv;
	}
	@RequestMapping("/supplier/save/")
	public ModelAndView saveSupplier(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("address") String address) {
System.out.println("saveSupplier method is calling");
		ModelAndView mv = new ModelAndView("redirect:/viewsuppliers");
		supplier.setId(id);
		supplier.setName(name);
		supplier.setAddress(address);
		if (supplierDAO.save(supplier)) {
			mv.addObject("supplierSuccessMessage", "The supplier created successfully");
			System.out.println("supplier saved");
		} else {
			mv.addObject("supplierErrorMessage", "Coulc not able to create supplier.  please contact admin");
		}
		return mv;
	}
	@PutMapping("/supplier/update/")
	public ModelAndView updateSupplier(@ModelAttribute Supplier supplier) {
		ModelAndView mv = new ModelAndView("index");
		if (supplierDAO.update(supplier) == true) {

			mv.addObject("successMessage", "The supplier updated successfully");
		} else {
			mv.addObject("errorMessage", "Could not update the supplier.");
		}
		return mv;
	}
	@RequestMapping("/supplier/delete/")
	public ModelAndView deleteSupplier(@RequestParam String id) {
		ModelAndView mv = new ModelAndView("redirect:/viewsuppliers");
		if (supplierDAO.delete(id) == true) {
			mv.addObject("supplierSuccessMessage", "The supplier deleted successfully");
		} else {
			mv.addObject("supplierErrorMessage", "Could not delete the supplier.");
		}
		return mv;
	}
	@RequestMapping("/supplier/edit/")
	public ModelAndView editSupplier(@RequestParam String id) {
		ModelAndView mv = new ModelAndView("redirect:/viewsuppliers");
		supplier = supplierDAO.get(id);
		httpSession.setAttribute("supplier", supplier);
		return mv;
	}
	@GetMapping("/supplier")
	public ModelAndView getAllSuppliers() {
		ModelAndView mv = new ModelAndView("index ");
		List<Supplier>	supplier = supplierDAO.list();
		mv.addObject("supplier", supplier);
		return mv;
	}
}

