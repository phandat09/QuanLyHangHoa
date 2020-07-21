package com.inventory_manegement.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.inventory_manegement.entities.History;
import com.inventory_manegement.repositories.HistoryRepository;

@Service
public class HistoryService extends BaseService {

	@Autowired HistoryRepository historyRepository;
	@Autowired private EntityManager entityManager;
	public HistoryRepository getHistoryRepository() {
		return historyRepository;
	}

	public void setHistoryRepository(HistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}
	
	public Map<String, Object> getHistory(final Map<String, Object> data) {
		String jpql = "SELECT c FROM History AS c WHERE  1 = 1"; 
		String jpqlTotal = "SELECT count(c.id) FROM History AS c WHERE 1 = 1";
		
		if(data.containsKey("name") && !String.valueOf(data.get("name")).isEmpty() && data.get("name") != "" ) {
			jpql = jpql + " AND c.productInfors.name LIKE :name ";
			jpqlTotal = jpqlTotal + " AND c.productInfors.name LIKE :name ";
		}
		if(data.containsKey("status") && !String.valueOf(data.get("status")).isEmpty()) {
			if(String.valueOf(data.get("status")) == "all");
			if(String.valueOf(data.get("status")) == "1") {
				jpql = jpql + " AND c.type = 1 ";
				jpqlTotal = jpqlTotal + " AND c.type = 1";
			}
			if(String.valueOf(data.get("status")) == "2") {
				jpql = jpql + " AND c.type = 1 ";
				jpqlTotal = jpqlTotal + " AND c.type = 2";
			}
		}
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(data.containsKey("name") && !String.valueOf(data.get("name")).isEmpty() ) parameters.put("name", "%" + data.get("name") + "%");
		if(data.containsKey("page") && !String.valueOf(data.get("page")).isEmpty() ) parameters.put("page", data.get("page"));
		
		Map<String, Object> results = new HashMap<String, Object>();

		// list
		results.put("history", executeListQuery(jpql, History.class, parameters));
		
		// total
		parameters.remove("page");
		long numberOfRecords = executeSingleQuery(jpqlTotal, Long.class, parameters);
		results.put("numberOfRecords", numberOfRecords);
		results.put("totalPages", totalPages(numberOfRecords));
		
		// return
		return results;
	}
	
	/**
	 * tính tổng số lần xuất kho
	 * @return
	 */
	public long getTotalExport() {
		String jpqlTotal = "SELECT count(c.id) FROM History AS c WHERE 1 = 1  and c.type = 2";
		return executeSingleQuery(jpqlTotal, Long.class, Collections.emptyMap());
	}
	
	/**
	 *  thống kê số lần xuất kho theo tháng trong năm
	 * @param nam
	 * @return 
	 * @return
	 */
	public  Map<Integer, Integer> statistical(int nam,int type) {
		String sql = "select sum(qty) , month(create_datetime) as thang " + 
				"from history  " + 
				"where year(create_datetime) ="+ nam +" and type ="+type + 
				"  group by month(create_datetime)  " + 
				"order by  month(create_datetime) "; 
		Map<Integer , Integer> map = new HashMap<Integer, Integer>();
		
		Query query = entityManager.createNativeQuery(sql);
		JSONObject jsonObject = null;
		try {
			
		jsonObject = new JSONObject();
		@SuppressWarnings("unchecked")
		List<Object[]> listPost =query.getResultList();
		for (Object[] a : listPost) {
			map.put(Integer.parseInt(String.valueOf(a[1])), Integer.parseInt(String.valueOf(a[0])));
			jsonObject.put(String.valueOf(a[1]), Integer.parseInt(String.valueOf(a[0])));
		}
		} catch(Exception exception) {
			System.out.println(exception);
		}
		 
		 String results = jsonObject.toString();
		return map;
	}
	
}
