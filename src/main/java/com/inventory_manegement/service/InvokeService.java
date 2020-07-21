package com.inventory_manegement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory_manegement.repositories.InvokeRepository;

@Service
public class InvokeService extends BaseService {

	@Autowired
	private InvokeRepository invokeRepository;

	public InvokeRepository getInvokeRepository() {
		return invokeRepository;
	}

	public void setInvokeRepository(InvokeRepository invokeRepository) {
		this.invokeRepository = invokeRepository;
	}
	

	
	
}
