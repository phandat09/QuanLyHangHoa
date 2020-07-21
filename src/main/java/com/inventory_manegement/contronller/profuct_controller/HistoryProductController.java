package com.inventory_manegement.contronller.profuct_controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory_manegement.service.HistoryService;

@Controller
public class HistoryProductController {
	@Autowired private HistoryService historyService;

	@GetMapping("/history")
	public String getHistory(final ModelMap modelMap , final HttpServletRequest request , final HttpServletResponse response) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("page", 1);
		modelMap.addAllAttributes(data);
		
		modelMap.addAllAttributes(historyService.getHistory(data));
		
		return "/ProductinStock/historyProduct";
	}
	@PostMapping(value = "/history")
	public String searchCategory(final ModelMap modelMap,final HttpServletRequest request, final HttpServletResponse response
			, @RequestParam final Map<String, Object> data) {
		modelMap.addAllAttributes(data);
		modelMap.addAllAttributes(historyService.getHistory(data));
		return "/ProductinStock/historyProduct";
		
	}
}
