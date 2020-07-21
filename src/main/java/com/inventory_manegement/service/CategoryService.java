package com.inventory_manegement.service;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inventory_manegement.entities.Category;
import com.inventory_manegement.repositories.CategoryRepository;

@Service
public class CategoryService  extends BaseService{
	@Autowired CategoryRepository categoryRepository;
	@Value("${attachment.path}")
	private String attachmentPath;

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public Map<String, Object> searchContacts(final Map<String, Object> data) {
		String jpql = "SELECT c FROM Category AS c WHERE  1 = 1"; 
		String jpqlTotal = "SELECT count(c.id) FROM Category AS c WHERE 1 = 1";
		
		if(data.containsKey("name") && !String.valueOf(data.get("name")).isEmpty() ) {
			jpql = jpql + " AND c.name LIKE :name ";
			jpqlTotal = jpqlTotal + " AND c.name LIKE :name ";
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(data.containsKey("name") && !String.valueOf(data.get("name")).isEmpty() ) parameters.put("name", "%" + data.get("name") + "%");
		if(data.containsKey("page") && !String.valueOf(data.get("page")).isEmpty() ) parameters.put("page", data.get("page"));
		
		Map<String, Object> results = new HashMap<String, Object>();

		// list
		results.put("listCategories", executeListQuery(jpql, Category.class, parameters));
		
		// total
		parameters.remove("page");
		long numberOfRecords = executeSingleQuery(jpqlTotal, Long.class, parameters);
		results.put("numberOfRecords", numberOfRecords);
		results.put("totalPages", totalPages(numberOfRecords));
		
		// return
		return results;
	}
	@Transactional(rollbackOn = RollbackException.class)
	public void saveCategories(final Category category , MultipartFile image) {
			try {
				if(image != null && image.getSize() > 0) {
				image.transferTo(new File(attachmentPath + image.getOriginalFilename()));
				
				category.setFile_path(attachmentPath + image.getOriginalFilename());
				category.setFileName(image.getOriginalFilename());
				category.setSize(String.valueOf(image.getSize()));
				category.setTpye(image.getContentType());
				
				categoryRepository.save(category);
				
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}
