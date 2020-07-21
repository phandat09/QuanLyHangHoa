package com.inventory_manegement.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inventory_manegement.entities.ProductInfor;
import com.inventory_manegement.repositories.ProductInforRepository;

@Service
public class ProductService {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ProductInforRepository inforRepository;
	
	@Value("${attachment.path}")
	private String attachmentPath;

	private final int limit = 20;

	private long totalPage(long numberOfRecord) {
		return (numberOfRecord - 1 + limit) / limit;
	}

	@SuppressWarnings("unchecked")
	private <T> T executeListQuerry(String jpql, Class<?> clazz, Map<String, Object> parameters) {
		Query query = entityManager.createQuery(jpql);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			if ("page".equals(entry.getKey()))
				continue;
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (parameters.containsKey("page") && !String.valueOf(parameters.containsKey("page")).isEmpty()) {
			int pageNumner = Integer.valueOf(String.valueOf(parameters.get("page")));
			query.setFirstResult((pageNumner - 1) * limit);
			query.setMaxResults(limit);
		}
		try {
			return (T) query.getResultList();
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	private <T> T executeSingleQuery(String jpql, Class<ProductInfor> clazz, Map<String, Object> parametters) {
		Query query = entityManager.createQuery(jpql);
		for (Map.Entry<String, Object> entry : parametters.entrySet()) {
			if ("page".equals(entry.getKey()))
				continue;
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (parametters.containsKey("page") && !String.valueOf(parametters.get("page")).isEmpty()) {
			int numberPage = Integer.valueOf(String.valueOf(parametters.get("page")));
			query.setFirstResult((numberPage - 1) * limit);
			query.setMaxResults(limit);
		}
		try {
			return (T) query.getSingleResult();
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Map<String, Object> getAllProduct(final Map<String, Object> map) {
		String jpql = "select p From ProductInfor as p where 1 =1 ";
		String totalRecord = " select count(p.id) from ProductInfor as p where 1=1";

		if (map.containsKey("name") && !String.valueOf(map.get("name")).isEmpty()) {
			jpql += "AND p.name like :name";
			totalRecord += "AND p.name like :name";
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(map.containsKey("name") && !String.valueOf(map.get("name")).isEmpty()) data.put("name", "%" + map.get("name") + "%");
		if(map.containsKey("page") && !String.valueOf(map.get("page")).isEmpty()) data.put("page", map.get("page") );
		
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("ProductInforList", executeListQuerry(jpql, ProductInfor.class, data));
		
		data.remove("page");
		
		long numberRecord = executeSingleQuery(totalRecord, ProductInfor.class, data);
		results.put("numberOfRecords", numberRecord);
		results.put("totalaPage", totalPage(numberRecord));
		
		return results;
	}
	
	public void saveProducInfo(final ProductInfor productInfor , final MultipartFile image) {
		
			try {
				productInfor.setCode(productInfor.getName());
				if(image != null && image.getSize() > 0) {
				image.transferTo(new File(attachmentPath + image.getOriginalFilename()));
				productInfor.setImgUrl(attachmentPath + image.getOriginalFilename());
				inforRepository.save(productInfor);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	public ProductInforRepository getInforRepository() {
		return inforRepository;
	}

	public void setInforRepository(ProductInforRepository inforRepository) {
		this.inforRepository = inforRepository;
	}
	
}
