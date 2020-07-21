package com.inventory_manegement.entities;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "menu")
public class Menu extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7561755650870682175L;
	
	
	@Column(name = "url" , length = 145)
	private String url;
	@Column(name = "name" , length = 45)
	private String name;
	@Column(name = "order_index")
	private String icons;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.LAZY , orphanRemoval = true)
	List<Auth> auths = new ArrayList<Auth>();
	
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent_id")
	private Menu manager;
	
	// dữ quá ít nên có thể dùng EAGER để lấy toàn bộ categories con.
	@OneToMany(mappedBy="manager", fetch = FetchType.EAGER)
	private Set<Menu> childrenMenu = new HashSet<Menu>();
	
	public void addAuth( final Auth auth) {
		auths.add(auth);
		auth.setMenu(this);
	}
	
	public void removeAuth(final Auth auth) {
		auths.remove(auth);
		auth.setMenu(null);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	public List<Auth> getAuths() {
		return auths;
	}

	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}

	public Menu getManager() {
		return manager;
	}

	public void setManager(Menu manager) {
		this.manager = manager;
	}

	public Set<Menu> getChildrenMenu() {
		return childrenMenu;
	}

	public void setChildrenMenu(Set<Menu> childrenMenu) {
		this.childrenMenu = childrenMenu;
	}
	
	
	
}
