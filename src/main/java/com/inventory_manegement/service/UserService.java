package com.inventory_manegement.service;


import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory_manegement.entities.Users;
import com.inventory_manegement.repositories.UserRepository;

@Service
/**
 * đánh dấu đây là service
 * @author PHAN DAT
 *
 */
@Transactional(rollbackOn = RollbackException.class )
public class UserService extends BaseService {
	
	@Autowired UserRepository userRepository;	
	@PersistenceContext protected EntityManager entityManager;
	public Object getAccount(String userName){
		String jpql ="select u from Users u where 1 =1";
		if(userName != null) {
			jpql += " and user_name = :name";
		}
		Query query = entityManager.createQuery(jpql,Users.class);
		query.setParameter("name", userName);
		return query.getSingleResult();
	}
	
	public List<Users> getUser(String userName){
		String jpql="select u from Users u where u.activeFlag =1 and u.userName = '"+userName+"'";
		return executeListQuery(jpql, Users.class, Collections.emptyMap());
	}
	public Long checkUserName(String userName) {
		String jpql="select count(u.id) from Users u where u.activeFlag =1 and u.userName = '"+userName+"'";
		return executeSingleQuery(jpql, Long.class, Collections.emptyMap());
	}
	public Long countTotalUser(String table) {
		String jqpl ="select count(u.id) from "+ table +" as u where 1=1";
		long record = executeSingleQuery(jqpl, Long.class, Collections.emptyMap());
		return record;
	}
	public UserRepository getUserRepository() {
		return userRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<Users> getAll(){
		String jpql = "select u from Users u where 1 =1 ";
		
		return executeListQuery(jpql, Users.class, Collections.emptyMap());
	}
	
}
