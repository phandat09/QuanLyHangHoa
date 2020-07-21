package com.inventory_manegement.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="role")
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4413403116511374551L;

	@Column(name = "role_name" , length = 45)
	private String roleName;
	@Column(name = "description" , length = 100)
	private String description;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "roles")
	private Set<Users> users = new HashSet<Users>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.LAZY , orphanRemoval = true)
	List<Auth> auths = new ArrayList<Auth>();
	public void addAuth(final Auth auth) {
		auths.add(auth);
		auth.setRole(this);
	}
	public void removeAuth(final Auth auth) {
		auths.remove(auth);
		auth.setRole(null);
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Users> getUsers() {
		return users;
	}
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	public List<Auth> getAuths() {
		return auths;
	}
	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}
	
	
	
	
}
