package com.inventory_manegement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2312411154023787119L;

	@Column(name = "name", length= 45)
	private String name;
	@Column(name = "code" , length = 145)
	private String code;
	@Lob
	@Column(name = "description" ,columnDefinition = "text")
	private String description;
	
	@Lob
	@Column(name = "file_path" ,columnDefinition = "text")
	private String file_path;
	@Column(name = "file_name", length= 45)
	private String fileName;
	@Column(name = "size", length= 45)
	private String size;
	@Column(name = "type", length= 45)
	private String tpye;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categorys", fetch = FetchType.LAZY , orphanRemoval = true)
	private List<ProductInfor> productInfors = new ArrayList<ProductInfor>();
	
	public void addProductInfor(final ProductInfor productInfor) {
		productInfors.add(productInfor);
		productInfor.setCategorys(this);
	}
	public void removeProductInfor(final ProductInfor productInfor) {
		productInfors.remove(productInfor);
		productInfor.setCategorys(null);
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
	public List<ProductInfor> getProductInfors() {
		return productInfors;
	}
	public void setProductInfors(List<ProductInfor> productInfors) {
		this.productInfors = productInfors;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getTpye() {
		return tpye;
	}
	public void setTpye(String tpye) {
		this.tpye = tpye;
	}
	
	
	
}
