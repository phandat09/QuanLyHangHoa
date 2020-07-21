package com.inventory_manegement.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History  extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4124545541219321080L;
	@Column(name = "action_name", length= 145)
	private String actionName;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "qty")
	private Integer quality;
	
	@Column(name = "price")
	private BigDecimal price;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="product_infor_id" )
	private ProductInfor productInfors;
	
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductInfor getProductInfors() {
		return productInfors;
	}

	public void setProductInfors(ProductInfor productInfors) {
		this.productInfors = productInfors;
	}
	
	

	


	
	
	
	
}
