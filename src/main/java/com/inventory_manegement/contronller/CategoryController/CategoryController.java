package com.inventory_manegement.contronller.CategoryController;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.inventory_manegement.entities.Category;
import com.inventory_manegement.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired CategoryService categoryService;
		@GetMapping(value = "/category/list")
		public String listCategory(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("page", 1);
			modelMap.addAllAttributes(data);
			
			modelMap.addAllAttributes(categoryService.searchContacts(data));
			
			return "Categories/listCategory";
		}
		
		@PostMapping(value = "/category/list")
		public String searchCategory(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response
				, @RequestParam final Map<String, Object> data) {
			modelMap.addAllAttributes(data);
			modelMap.addAllAttributes(categoryService.searchContacts(data));
			return "Categories/listCategory";
			
		}
		@GetMapping(value = "/category/list/add")
		public String addCategory(final ModelMap modelMap, final HttpServletRequest request, final HttpServletResponse response) {
			modelMap.addAttribute("cate", new Category());
			return "Categories/add";
		}
		
		@PostMapping(value = "/category/list/add")
		public String addCategories(final ModelMap modelMap, final HttpServletRequest request, final HttpServletResponse response
				,@ModelAttribute(name = "cate") Category cate, @RequestParam("postImage") MultipartFile image) {
			Category category = cate;
			
			category.setName(cate.getName().toUpperCase());
			category.setCode(cate.getName().toLowerCase());
			try {
				if(checkCategories(category.getName())) {
					categoryService.saveCategories(category, image);
					modelMap.addAttribute("message", "ADD Success");
				} 
				else  {
					modelMap.addAttribute("messageError", "ADD FAIL SAME NAME ");
				}
			} catch (Exception e) {
				modelMap.addAttribute("messageError", "ADD FAIL ");
			}
			
			return "Categories/add";
		}
		
		@GetMapping(value = "category/list/edit/{id}")
		public  String edit(final ModelMap modelMap, final HttpServletRequest request, final HttpServletResponse response
				,final @PathVariable int id) {
			Category category = categoryService.getCategoryRepository().findById(id).get();
			modelMap.addAttribute("cate",  category);
			return "Categories/update";
		}
		
		@PostMapping(value = "/category/list/edit")
		public String update(final ModelMap modelMap, final HttpServletRequest request, final HttpServletResponse response
				,@ModelAttribute Category cate , @RequestParam("postImage") MultipartFile image) {
			cate.setName(cate.getName().toUpperCase());
			cate.setCode(cate.getName().toLowerCase());
			
			try {
				categoryService.saveCategories(cate, image);
				modelMap.addAttribute("message", "UPDATE Success");
			} catch (Exception e) {
				modelMap.addAttribute("messageError", "UPDATE FAIL ");
			}
			modelMap.addAttribute("cate", cate);
			return "Categories/update";
		}
		private boolean checkCategories(String name) {
			boolean flag = true;
			List<Category> categories = categoryService.getCategoryRepository().findAll();
			for(Category category :categories) {
				if(name.equals(category.getName())) flag = false;
					
			}
			return flag;
		}
}
