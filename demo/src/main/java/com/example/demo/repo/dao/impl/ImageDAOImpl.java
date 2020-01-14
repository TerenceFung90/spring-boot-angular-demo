package com.example.demo.repo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.repo.dao.ImageDAOCustom;
import com.example.demo.repo.model.Image;

@Repository
public class ImageDAOImpl implements ImageDAOCustom {

	@PersistenceContext
    EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Image> findByCriteriaAndPage(Boolean autoCorrect, Integer pageNumber, Integer pageSize, String q,
			Boolean safeSearch) {
		
		Query query = entityManager.createQuery("From Image");
		query.setFirstResult((pageNumber-1) * pageSize); 
		query.setMaxResults(pageSize);
		
		return query.getResultList();
	}

}
