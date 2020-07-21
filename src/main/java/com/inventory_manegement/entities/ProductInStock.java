package com.inventory_manegement.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name ="product_in_stock")
public class ProductInStock extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7153219717474660205L;

	@Column(name = "qty")
	private Integer quality;
	@Column(name = "price")
	private BigDecimal price;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="product_infor_id" )
	private ProductInfor productInfors;
	
	public ProductInfor getProductInfors() {
		return productInfors;
	}
	public void setProductInfors(ProductInfor productInfors) {
		this.productInfors = productInfors;
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
	
	
}
