package com.example.demo.repo.dao;

import java.util.List;

import com.example.demo.repo.model.Image;

public interface ImageDAOCustom {

	List<Image> findByCriteriaAndPage(Boolean autoCorrect, Integer pageNumber, Integer pageSize, String q,
			Boolean safeSearch);
}
