package com.inventory_manegement.contronller.profuct_controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.inventory_manegement.entities.Category;
import com.inventory_manegement.entities.ProductInfor;
import com.inventory_manegement.repositories.CategoryRepository;
import com.inventory_manegement.service.ProductService;

@Controller
@RequestMapping("/product-info")
public class ProducInfoController {
	@Autowired ProductService productService;
	@Autowired CategoryRepository categoryRepository;
	
	// lấy danh sách các sản phẩm 
	@GetMapping("/list")
	public String ViewAllProduct(final ModelMap modelMap , final HttpServletRequest httpServletRequest , final HttpServletResponse httpServletResponse) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("page", 1);
		
		modelMap.addAllAttributes(data);
		modelMap.addAllAttributes(productService.getAllProduct(data));
		
		return "Product/view";
	}
	// thực hiện việc search dữ liệu và phân trang
	@PostMapping("/list")
	public String ViewProduct(final ModelMap modelMap , final HttpServletRequest httpServletRequest , final HttpServletResponse httpServletResponse,
			@RequestParam Map<String, Object> data) {
		modelMap.addAllAttributes(data);
		modelMap.addAllAttributes(productService.getAllProduct(data));
		return "Product/view";
	}
	
	// request đến trang add 
	@GetMapping("/add")
	public String addProduct(final ModelMap map,final HttpServletRequest httpServletRequest , final HttpServletResponse httpServletResponse) {
		map.addAttribute("pi", new ProductInfor());
		List<Category> listCategories = categoryRepository.findAll();
		map.addAttribute("categories",listCategories);
		return "Product/add";
	}
	// thêm sản phầm vào csdl
	@PostMapping("/add")
	public String addProductwithInfo(final ModelMap map,final HttpServletRequest httpServletRequest , final HttpServletResponse httpServletResponse
			, @ModelAttribute("pi") ProductInfor pi , final @RequestParam("postImage") MultipartFile file) {
		try {
			pi.setCode(pi.getName().toLowerCase());
			productService.saveProducInfo(pi, file);
			map.addAttribute("message", "Thêm Sản Phầm Vào thành công");
			
		} catch (Exception e) {
			map.addAttribute("messageError", "Thêm Sản Phầm Vào Thất Bại");
		}
		return "Product/add";
	}
	
	// lấy sản phẩm theo id của n chuyển sang trang edit
	@GetMapping("/edit/{prodcutID}")
	public String getProductToEdit(final ModelMap map,final HttpServletRequest httpServletRequest , final HttpServletResponse httpServletResponse
			,@PathVariable("prodcutID") Integer productId) {
		ProductInfor PI = new ProductInfor();
		PI = productService.getInforRepository().findById(productId).get();
		List<Category> listCategories = categoryRepository.findAll();
		map.addAttribute("categories",listCategories);
		File file = new File(PI.getImgUrl());
		try {
			
		if(file.delete()) {
			System.out.println("Xóa Ảnh Thành Công");
		}
		} catch (Exception e) {
			System.out.println("Xóa Ảnh không thành công");
		}
		map.addAttribute("pi",PI);
		
		
		return "Product/edit";
	}
	
	@PostMapping("/edit")
	public String productToEdit(final ModelMap map,final HttpServletRequest httpServletRequest , final HttpServletResponse httpServletResponse
			,@ModelAttribute ProductInfor pi,final @RequestParam("postImage") MultipartFile file) {
		try {
			productService.saveProducInfo(pi, file);
			return "redirect:/product-info/list";
		} catch (Exception e) {
			map.addAttribute("messageError", e.getMessage());
			return "Product/edit";
		}
		
	}
}
