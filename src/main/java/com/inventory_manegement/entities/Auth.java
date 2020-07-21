package com.inventory_manegement.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "auth")
public class Auth extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3721859556634059364L;
	@Column(name = "permssion")
	private Integer permssion;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="role_id")
	private Role role;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="menu_id")
	private Menu menu;
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Integer getPermssion() {
		return permssion;
	}
	public void setPermssion(Integer permssion) {
		this.permssion = permssion;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	
	
}
