package com.inventory_manegement.service;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class BaseService {
	@PersistenceContext protected EntityManager entityManager; // như là 1 database quản lí các entities.
	
	protected static final int LIMIT = 10;
	
	protected long totalPages(long numberOfRecords) {
		return (numberOfRecords + LIMIT - 1) / LIMIT;
	}
	
	@SuppressWarnings("unchecked")
	protected <t> t executeListQuery(String jqpl, Class<?> clazz, Map<String, Object> parameters) {
		Query query = entityManager.createQuery(jqpl, clazz);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			if("page".equals(entry.getKey())) continue;
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if(parameters.containsKey("page") && !String.valueOf(parameters.get("page")).isEmpty() ) {
			int pageNumber = Integer.valueOf(String.valueOf(parameters.get("page")));
			query.setFirstResult((pageNumber-1) * LIMIT); 
			query.setMaxResults(LIMIT);
		}
		try {
			return (t) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <t> t executeSingleQuery(String jqpl, Class<?> clazz, Map<String, Object> parameters) {
		Query query = entityManager.createQuery(jqpl, clazz);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			if("page".equals(entry.getKey())) continue;
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if(parameters.containsKey("page") && !String.valueOf(parameters.get("page")).isEmpty() ) {
			int pageNumber = Integer.valueOf(String.valueOf(parameters.get("page")));
			query.setFirstResult((pageNumber-1) * LIMIT); // thay thể offset // vị trí bắt đầu lấy giữ liệu
			query.setMaxResults(LIMIT); // số record để lấy
		}
		try {
			return (t) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
