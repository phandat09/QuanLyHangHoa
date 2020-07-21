package com.inventory_manegement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory_manegement.repositories.RoleRepository;

@Service 
public class RoleServices  extends BaseService{

	@Autowired RoleRepository roleRepository;

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
}
