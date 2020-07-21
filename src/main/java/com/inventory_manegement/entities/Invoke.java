package com.inventory_manegement.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="invoke")
public class Invoke extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8259513647888221451L;

	@Column(name = "type")
	private Integer type;
	@Lob
	@Column(name = "code" ,columnDefinition = "text")
	private String code;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
