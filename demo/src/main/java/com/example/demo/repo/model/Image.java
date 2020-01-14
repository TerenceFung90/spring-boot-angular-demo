package com.example.demo.repo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Image")
@Data
public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition="bigint")
	private Long id;
	
	@Column(columnDefinition="varchar(500)")
	private String url;
	
	@Column(columnDefinition="integer")
	private Integer height;
	
	@Column(columnDefinition="integer")
	private Integer width;
	
	@Column(columnDefinition="varchar(500)")
	private String thumbnail;
	
	@Column(columnDefinition="integer")
	private Integer thumbnailHeight;
	
	@Column(columnDefinition="integer")
	private Integer thumbnailWidth;
	
}
