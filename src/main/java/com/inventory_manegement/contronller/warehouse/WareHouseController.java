package com.inventory_manegement.contronller.warehouse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory_manegement.entities.History;
import com.inventory_manegement.entities.Invoke;
import com.inventory_manegement.entities.ProductInStock;
import com.inventory_manegement.service.HistoryService;
import com.inventory_manegement.service.InvokeService;
import com.inventory_manegement.service.ProductInStockService;
import com.inventory_manegement.service.ProductService;

@Controller
public class WareHouseController {

	@Autowired ProductService productService;
	@Autowired private InvokeService invokeService;
	@Autowired public ProductInStockService inStockService;
	@Autowired private HistoryService historyService;
	// Nhập Sản phẩm vào kho
	@GetMapping("/goods-recept/list")
	public String importProduct(final ModelMap modelMap , final HttpServletRequest request , HttpServletResponse response) {
		
		modelMap.addAttribute("invoke", new Invoke());
		modelMap.addAttribute("product", productService.getInforRepository().findAll());
		return "warehouse/importWareHouse";
	}
	
	/**
	 * lấy dữ liệu từ client để lưu vào kho và lịch sử nhập kho
	 * @param map
	 * @param request
	 * @param response
	 * @param invoke
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @return
	 */
	@PostMapping("/goods-recept/list")
	public String save(final ModelMap map, final HttpServletRequest request,final HttpServletResponse response
			,@ModelAttribute Invoke invoke , @RequestParam String firstName , @RequestParam String lastName , @RequestParam String address ) {
		String code = firstName+" " + lastName+"---" + address;
		invoke.setCode(code);
		try {
		// save vào lịch sử xuất nhập cho kho
		History history = new History();
		history.setPrice(invoke.getPrice());
		history.setActionName(code);
		history.setProductInfors(invoke.getProductInfors());
		history.setQuality(invoke.getQuality());
		history.setType(invoke.getType());
		
		historyService.getHistoryRepository().save(history);
		
		// xử lý việc nhập kho cho sản phẩm
		// check xem sản phẩm đã có trong kho chưa nếu chưa thì tạo mới , nếu có rồi thì cộng thêm số lượng vào cho sản phẩm
		Integer productID = invoke.getProductInfors().getId();
		Long record = inStockService.getRecordProductInStock(productID);
		if(record > 0) {
			ProductInStock productInStock = new ProductInStock();
			productInStock = inStockService.getProduct(productID);
			productInStock.setQuality(productInStock.getQuality() + invoke.getQuality());
			
			inStockService.getInStockRepository().save(productInStock);
		} else {
			ProductInStock inStock = new ProductInStock();
			inStock.setQuality(invoke.getQuality());
			inStock.setPrice(invoke.getPrice());
			inStock.setProductInfors(invoke.getProductInfors());
			
			inStockService.getInStockRepository().save(inStock);
		}
		
		// save công việc nhập hàng vào cho kho
		invokeService.getInvokeRepository().save(invoke);
		}catch (Exception e) {
			map.addAttribute("messageError", "Bạn Vừa Nhập Kho lỗi ");
		}
		map.addAttribute("message", "bạn vừa nhập kho thành công cho sản phẩm "+invoke.getProductInfors().getName() + "--với số lượng là :"+invoke.getQuality() );
		return "warehouse/importWareHouse";
	}
	
	/**
	 * 
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/goods-issue/list")
	public String issue(final ModelMap modelMap, final HttpServletRequest request,final HttpServletResponse response) {
		
		modelMap.addAttribute("invoke", new Invoke());
		modelMap.addAttribute("product", inStockService.getProductInStock());
		return "warehouse/expoprtWareHouse";
	}
	/**
	 * lưu dữ liệu xuất kho của sản phẩm
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param invoke
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @return
	 */
	@PostMapping("/goods-issue/list")
	public String issueSave(final ModelMap modelMap, final HttpServletRequest request,final HttpServletResponse response
			,@ModelAttribute Invoke invoke , @RequestParam String firstName , @RequestParam String lastName , @RequestParam String address) {
		String code = firstName+" " + lastName+"---" + address;
		invoke.setCode(code);
		try {
			// lưu vào lịch sử của việc xuất kho
			History history = new History();
			history.setPrice(invoke.getPrice());
			history.setActionName(code);
			history.setProductInfors(invoke.getProductInfors());
			history.setQuality(invoke.getQuality());
			history.setType(invoke.getType());
			
			historyService.getHistoryRepository().save(history);
			
			// lấy thông tin sản phẩm vừa nhập vào
			Integer productID = invoke.getProductInfors().getId();
			ProductInStock productInStock = inStockService.getProduct(productID);
			// kiểm tra số lượng xuất ra và số lượng sản phâm có  trong kho
			// nếu số lượng lớn hơn hoặc bằng thì cho xuất kho còn không thì sẽ thông báo lỗi
			if(productInStock.getQuality() < invoke.getQuality()) {
				modelMap.addAttribute("messageError", "Số lượng bạn muốn lấy ra quá số lượng cho phép ");
				return "redirect:/goods-issue/list";
			} else {
				productInStock.setQuality(productInStock.getQuality() - invoke.getQuality());
				inStockService.getInStockRepository().save(productInStock);
				invokeService.getInvokeRepository().save(invoke);
				modelMap.addAttribute("message", "bạn vừa Xuất kho thành công cho sản phẩm "+invoke.getProductInfors().getName() + "--với số lượng là :"+invoke.getQuality() );
			}
			
		}catch (Exception e) {
			modelMap.addAttribute("messageError", "Có lỗi xảy ra");
		}
		
		return "warehouse/expoprtWareHouse";
	}
}
