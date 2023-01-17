//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kafein.envanter.core.persistence.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractEntity implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Transient
	private Long currentpage;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @CreatedDate
    @Column(
            name = "created_date",
            nullable = false,
            updatable = false
    )
    private ZonedDateTime createdDate = ZonedDateTime.now();
    @LastModifiedDate
    @Column(
            name = "updated_date"
    )

    @JsonIgnore
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();
    @CreatedBy
    @Column(
            name = "created_by",
            updatable = false
    )
    private String createdBy;	
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public ZonedDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Long currentpage) {
		this.currentpage = currentpage;
	}
	
}
