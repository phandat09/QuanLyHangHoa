package com.inventory_manegement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product_infor")
public class ProductInfor extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1220627589083028384L;

	@Column(name = "name" , length = 45	)
	private String name;
	
	@Column(name = "code" , length =145)
	private String code;
	
	@Lob
	@Column(name = "desciption" , columnDefinition = "text")
	private String description;
	@Lob
	@Column(name = "img_url" , columnDefinition = "text")
	private String imgUrl;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category categorys;
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "productInfors",orphanRemoval = true)
	private List<ProductInStock> productInStocks = new ArrayList<ProductInStock>();
	
	public void addProductInStock(final ProductInStock productInStock) {
		productInStocks.add(productInStock);
		productInStock.setProductInfors(this);
	}
	
	public void removeProductinStock(final ProductInStock productInStock) {
		productInStocks.remove(productInStock);
		productInStock.setProductInfors(null);
	}
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "productInfors",orphanRemoval = true)
	private List<History> histories = new ArrayList<History>(); 
	
	public void addHistory(final History history) {
		histories.add(history);
		history.setProductInfors(this);
	}
	
	public void removeHistory(final History history) {
		histories.remove(history);
		history.setProductInfors(null);
	}
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "productInfors",orphanRemoval = true)
	private List<Invoke> invokes = new ArrayList<Invoke>();
	
	public void addInvoke(final Invoke invoke) {
		invokes.add(invoke);
		invoke.setProductInfors(this);
	}
	public void removeInvoke(final Invoke invoke) {
		invokes.remove(invoke);
		invoke.setProductInfors(null);
	}
	
	public Category getCategorys() {
		return categorys;
	}
	public void setCategorys(Category categorys) {
		this.categorys = categorys;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<ProductInStock> getProductInStocks() {
		return productInStocks;
	}

	public void setProductInStocks(List<ProductInStock> productInStocks) {
		this.productInStocks = productInStocks;
	}

	public List<History> getHistories() {
		return histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public List<Invoke> getInvokes() {
		return invokes;
	}

	public void setInvokes(List<Invoke> invokes) {
		this.invokes = invokes;
	}
	
	
}
