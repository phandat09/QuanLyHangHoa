package com.inventory_manegement.contronller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventory_manegement.entities.Auth;
import com.inventory_manegement.entities.Menu;
import com.inventory_manegement.entities.Role;
import com.inventory_manegement.entities.Users;
import com.inventory_manegement.service.HistoryService;
import com.inventory_manegement.service.UserService;
import com.inventory_manegement.utils.HttpUtils;

@Controller
public class HomeController {
	@Autowired UserService userService;
	@Autowired HistoryService history;
	@SuppressWarnings("deprecation")
	@GetMapping(value = "/home")
	public String goHome(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//HttpUtils.setSessionValue("useName", userDetails.getUsername(), request);
		Users user =userService.getUser(userDetails.getUsername()).get(0);
		Role roles = user.getRoles().iterator().next();
		List<Auth> listAuth = roles.getAuths();
		List<Menu> menu = new ArrayList<Menu>();
		for(Auth item : listAuth) {
				menu.add(item.getMenu());
		}
		HttpUtils.setSessionValue("useName", user, request);
		modelMap.addAttribute("users", userDetails.getUsername().toString());
		modelMap.addAttribute("totaluses", userService.countTotalUser("Users"));
		modelMap.addAttribute("totalProduct", userService.countTotalUser("ProductInfor"));
		modelMap.addAttribute("totalCategories", userService.countTotalUser("Category"));
		modelMap.addAttribute("totalExport", history.getTotalExport());
		HttpUtils.setSessionValue("menu", menu, request);
		
		Date date = new Date();
		modelMap.addAttribute("totalImport",history.statistical(date.getYear() + 1900,1));
		modelMap.addAttribute("totalEmport",history.statistical(date.getYear() + 1900,2));
		return "index";
	}
	
	
	
}
