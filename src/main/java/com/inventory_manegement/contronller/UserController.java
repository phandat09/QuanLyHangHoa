package com.inventory_manegement.contronller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventory_manegement.entities.Users;
import com.inventory_manegement.service.RoleServices;
import com.inventory_manegement.service.UserService;

@Controller
public class UserController {

	@Autowired private UserService userService;
	@Autowired private RoleServices roleServices;
	
	@GetMapping("/user/list")
	public String getAllUser(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response) {
		
		modelMap.addAttribute("listUsers", userService.getAll());
		
		return "users/view";
	}
	
	@GetMapping("/role/list")
	public String manager(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response) {
		
		modelMap.addAttribute("users", userService.getUserRepository().findAll());
		modelMap.addAttribute("roles", roleServices.getRoleRepository().findAll());
		
		return "users/managerRole";
	}
	
	@PostMapping("/roleEdit")
	public String managerEdit(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response
			,@ModelAttribute Users user) {
		
		
		return "users/managerRole";
	}
	
}
