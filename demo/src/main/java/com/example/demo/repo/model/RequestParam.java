package com.example.demo.repo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="RequestParam")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestParam implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition="bigint")
	private Long id;
	
	@Column(columnDefinition="boolean")
	private Boolean autoCorrect;
	@Column(columnDefinition="integer")
	private Integer pageNumber;
	@Column(columnDefinition="integer")
	private Integer pageSize; 
	@Column(columnDefinition="varchar(200)")
	private String q;
	@Column(columnDefinition="boolean")
	private Boolean safeSearch;
}
