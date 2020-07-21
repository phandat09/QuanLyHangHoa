package com.inventory_manegement.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory_manegement.entities.History;
import com.inventory_manegement.entities.ProductInStock;
import com.inventory_manegement.entities.ProductInfor;
import com.inventory_manegement.repositories.ProductInStockRepository;


@Service
@Transactional(rollbackOn = RollbackException.class )
public class ProductInStockService extends BaseService{

	@Autowired private ProductInStockRepository inStockRepository;

	public ProductInStockRepository getInStockRepository() {
		return inStockRepository;
	}

	public void setInStockRepository(ProductInStockRepository inStockRepository) {
		this.inStockRepository = inStockRepository;
	}
	
	public Long getRecordProductInStock(Integer productId) {
		String jpql = "select count(p.id) from ProductInStock p where 1=1 ";
		
		if(productId != null) {
			jpql += "and p.productInfors.id = :id";
		}
		Map<String , Object> param = new HashMap<String, Object>();
		if(productId != null) 	param.put("id", productId);
		Long record = executeSingleQuery(jpql, Long.class, param);
		
		return record;
	}
	/**
	 *  lấy sản sẩm trong kho bằng id của sản phẩm
	 * @param idProductInfo
	 * @return
	 */
	public ProductInStock getProduct(Integer idProductInfo) {
		
		String jpql ="select p from  ProductInStock p where 1=1";
		if(idProductInfo != null) {
			jpql += "and p.productInfors.id = :id";
		}
		Map<String , Object> param = new HashMap<String, Object>();
		if(idProductInfo != null) 	param.put("id", idProductInfo);
		
		return executeSingleQuery(jpql, ProductInStock.class, param);
	}
	/**
	 * lấy sản phẩn mà có trong kho
	 * @return
	 */
	public List<ProductInfor>  getProductInStock(){
		String jpql ="select p from ProductInfor p where p.id  in (select pi.productInfors.id from ProductInStock pi)";
		return executeListQuery(jpql, ProductInfor.class,Collections.emptyMap());
	}
	public Map<String, Object> getAll(final Map<String, Object> data) {
		String jpql = "SELECT c FROM ProductInStock AS c WHERE  1 = 1"; 
		String jpqlTotal = "SELECT count(c.id) FROM ProductInStock AS c WHERE 1 = 1";
		
		if(data.containsKey("name") && !String.valueOf(data.get("name")).isEmpty() ) {
			jpql = jpql + " AND c.productInfors.name LIKE :name ";
			jpqlTotal = jpqlTotal + " AND c.productInfors.name  LIKE :name ";
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(data.containsKey("name") && !String.valueOf(data.get("name")).isEmpty() ) parameters.put("name", "%" + data.get("name") + "%");
		if(data.containsKey("page") && !String.valueOf(data.get("page")).isEmpty() ) parameters.put("page", data.get("page"));
		
		Map<String, Object> results = new HashMap<String, Object>();

		// list
		results.put("products", executeListQuery(jpql, ProductInStock.class, parameters));
		
		// total
		parameters.remove("page");
		long numberOfRecords = executeSingleQuery(jpqlTotal, Long.class, parameters);
		results.put("numberOfRecords", numberOfRecords);
		results.put("totalPages", totalPages(numberOfRecords));
		
		// return
		return results;
	}
}
