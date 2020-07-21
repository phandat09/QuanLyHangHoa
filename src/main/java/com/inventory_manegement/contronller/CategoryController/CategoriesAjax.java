package com.inventory_manegement.contronller.CategoryController;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventory_manegement.contronller.Ajax.AjaxResonse;
import com.inventory_manegement.entities.Category;
import com.inventory_manegement.entities.ProductInStock;
import com.inventory_manegement.entities.ProductInfor;
import com.inventory_manegement.entities.Role;
import com.inventory_manegement.entities.Users;
import com.inventory_manegement.service.CategoryService;
import com.inventory_manegement.service.HistoryService;
import com.inventory_manegement.service.ProductInStockService;
import com.inventory_manegement.service.ProductService;
import com.inventory_manegement.service.RoleServices;
import com.inventory_manegement.service.UserService;

@Controller
@RequestMapping(value = "/api")
public class CategoriesAjax {
	
	@Autowired private CategoryService categoryService ;
	@Autowired private ProductService productService;
	@Autowired private ProductInStockService productInStockService;
	@Autowired private UserService userService;
	@Autowired private HistoryService historyService;
	@Autowired private RoleServices roleServices;
	@PostMapping(value = "/deleteCategory")
	public ResponseEntity<AjaxResonse> deleteCategories(final ModelMap modelMap, final HttpServletRequest request,final HttpServletResponse response
			,@RequestBody final Map<String, Object> data){
		try {
			Integer id = Integer.parseInt(String.valueOf(data.get("categoryId")));
			Category category = categoryService.getCategoryRepository().findById(id).get();
			
			category.setActiveFlag(0);
			
			categoryService.getCategoryRepository().save(category);
			return ResponseEntity.ok(new AjaxResonse(200, "succes"));
			
		} catch (Exception e) {
			return ResponseEntity.ok(new AjaxResonse(400,"fail" ));
		}
	}
	@PostMapping("/deleteProduct")
	public ResponseEntity<AjaxResonse> deleteProduct(final ModelMap modelMap, final HttpServletRequest request,final HttpServletResponse response
			,@RequestBody final Map<String, Object> data){
		try {
			Integer id = Integer.parseInt(String.valueOf(data.get("pruductID")));
			ProductInfor pi = productService.getInforRepository().findById(id).get();
			
			pi.setActiveFlag(0);
			productService.getInforRepository().save(pi);
			return ResponseEntity.ok(new AjaxResonse(200, "success"));
			
		}catch (Exception e) {
			return ResponseEntity.ok(new AjaxResonse(400,"fail" ));
		}
	}
	/**
	 * lấy số lượng sản phẩm khi ta chọn sản phẩm cần lấy ra
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@PostMapping("/getQualityProduct")
	public ResponseEntity<AjaxResonse> getQualityProduct(final ModelMap modelMap, final HttpServletRequest request,final HttpServletResponse response
			,@RequestBody final Map<String, Object> data){
		try {
			Integer id = Integer.parseInt(String.valueOf(data.get("productId")));
			ProductInStock inStock = productInStockService.getProduct(id);
			
			send send = new send();
			send.setQuality(inStock.getQuality());
			send.setPrice(inStock.getPrice());
			
			return ResponseEntity.ok(new AjaxResonse(200,send));
			
		}catch (Exception e) {
			return ResponseEntity.ok(new AjaxResonse(400,"fail" ));
		}
	}
	
	/**
	 * lấy lại thông tin về biều đồ thống kê xuất nhập xuất của sản phầm
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@PostMapping("/onchangeBar")
	public ResponseEntity<AjaxResonse> changeBar(final ModelMap modelMap, final HttpServletRequest request,final HttpServletResponse response
			,@RequestBody final Map<String, Object> data){
		try {
			Integer id = Integer.parseInt(String.valueOf(data.get("year")));
			 Map<Integer, Integer> statisticalimImport = historyService.statistical(id, 1);
			 Map<Integer, Integer> statisticalExport = historyService.statistical(id, 2);
			 
			 
			 data.put("import", statisticalimImport);
			 data.put("emport", statisticalExport);
			return ResponseEntity.ok(new AjaxResonse(200,data));
			
		}catch (Exception e) {
			return ResponseEntity.ok(new AjaxResonse(400,"fail" ));
		}
	}
	/**
	 * Xóa User muốn xóa theo id
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@PostMapping("/deleteUser")
	public ResponseEntity<AjaxResonse> deleteUser(final ModelMap modelMap, final HttpServletRequest request,final HttpServletResponse response
			,@RequestBody final Map<String, Object> data){
		try {
			Integer id = Integer.parseInt(String.valueOf(data.get("UserId")));
			userService.getUserRepository().deleteById(id);
			return ResponseEntity.ok(new AjaxResonse(200,"successs"));
			
		}catch (Exception e) {
			return ResponseEntity.ok(new AjaxResonse(400,"fail" ));
		}
	}
	@PostMapping("/getRole")
	public ResponseEntity<AjaxResonse> getRole(final ModelMap modelMap, final HttpServletRequest request,final HttpServletResponse response
			,@RequestBody final Map<String, Object> data){
		Integer idUser = Integer.parseInt(String.valueOf(data.get("userId")));
		Integer idRole = Integer.parseInt(String.valueOf(data.get("roleId")));
		
		Users user = userService.getUserRepository().findById(idUser).get();
		Set<Role> roles = new HashSet<Role>();;
		
		boolean flag = false;
		for (Role item : user.getRoles()) {
			
			if(item.getId() == idRole) {
				
			}
			else {
				Role role = roleServices.getRoleRepository().findById(idRole).get();
				roles.add(role);
				user.setRoles(roles);
				
				userService.getUserRepository().save(user);
				
				flag = true;
			}
		}
		if(flag == true) {
			 return ResponseEntity.ok(new AjaxResonse(400,"ok" ));
		}else {
			return ResponseEntity.ok(new AjaxResonse(400,"nothing" ));
			
		}
		
		
		
	}
}
class send<T,V>{
	private long quality;
	private BigDecimal price;
	public long getQuality() {
		return quality;
	}
	public void setQuality(long quality) {
		this.quality = quality;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
