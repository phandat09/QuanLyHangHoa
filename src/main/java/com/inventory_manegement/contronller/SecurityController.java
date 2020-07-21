package com.inventory_manegement.contronller;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventory_manegement.entities.Role;
import com.inventory_manegement.entities.Users;
import com.inventory_manegement.service.RoleServices;
import com.inventory_manegement.service.UserService;
import com.inventory_manegement.utils.HttpUtils;


@Controller
public class SecurityController extends BaseController {

	@Autowired UserService userService;
	@Autowired RoleServices roleServices;
	@RequestMapping(value = {"/login","/"})
	public String index(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response) {
		if(HttpUtils.getSessionValue("useName", request) !=  null) {
			
			return "redirect:/home";
		}else 
		return "inventory_management/login"; // view là trang login của mình thiết kế.
	}
	
	
	@GetMapping(value = "/register")
	public String register(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response) {
		modelMap.addAttribute("register", new Users());
		return "inventory_management/register";
	}
	
	@PostMapping(value = "/register")
	public String addUser(final ModelMap modelMap, final @ModelAttribute(name = "register") Users users) {
		try {
			Users user = users;
			Role role = roleServices.getRoleRepository().findById(2).get();
			Set<Role> roles = new HashSet<Role>();
			roles.add(role);
			user.setRoles(roles);
			if(userService.checkUserName(user.getUserName()) > 0) {
				modelMap.addAttribute("register", user);
				modelMap.addAttribute("messages", "Trùng userName bạn không thể đăng kí đc");
				return "inventory_management/register";
			}else  {
				modelMap.addAttribute("messages", "Bạn Đã Đăng kí thành công ");
				userService.getUserRepository().save(users);
				return "/users/view";
			}
			
		} catch (RollbackException e) {
			e.getMessage();
		}
		
		return "users/view";
	}

	@GetMapping(value = "/editUser/{id}")
	public String edit(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response,@PathVariable() int id) {
		modelMap.addAttribute("user", userService.getUserRepository().findById(id) );
		return "users/edit";
	}
	@PostMapping(value = "/editUser")
	public String editUser(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response,@ModelAttribute Users user) {
		
		userService.getUserRepository().save(user);
		modelMap.addAttribute("listUsers", userService.getAll());
		
		return "users/view";
	}
	@Override
	protected String name() {
		return "SecurityController";
	}

}
