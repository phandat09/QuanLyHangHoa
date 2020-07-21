package com.inventory_manegement.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table (name ="users")
public class Users extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4808623375363119957L;

	@Column(name ="user_name" , length =45)
	private String userName ;
	@Column(name ="password" , length =100)
	private String password ;
	
	@Column(name ="name" , length =100)
	private String name ;
	
	@Column(name ="email" , length =145)
	private String email ;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role"
		, joinColumns = @JoinColumn(name = "users_id")
		, inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<Role>();
	
	public void addRole(final Role role) {
		roles.add(role);
		role.getUsers().add(this);
	}
	
	public void removeRole(final Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
}
