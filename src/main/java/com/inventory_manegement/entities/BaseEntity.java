package com.inventory_manegement.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * class này là chứa các fields chung của hệ thống. Yêu cầu phải implement
 * Serializable.
 * 
 * @author admin
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = -8674444174400304255L;

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss,dd/MM/yyyy");
	
	@Id // xác định đây là khoá chính.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment.
	private Integer id; // primary-key

	@Column(name = "active_flag", nullable = false)
	private Integer activeFlag =1;

	@Column(name = "create_datetime")
	private LocalDateTime createDateTime;

	@Column(name = "update_datetime")
	private LocalDateTime updateDateTime;

	@JsonIgnore
	public String getStringCreateDateTime() {
	 if(createDateTime == null) return LocalDateTime.now().format(formatter);
		return createDateTime.format(formatter);
	}
	
	// ::: save
    @PrePersist
    private void onInsert() {
    	this.createDateTime = LocalDateTime.now();
    }
    
    // ::: update
    @PreUpdate
    private void onUpdate() {
    	this.updateDateTime = LocalDateTime.now();
    }
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}


	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
}
