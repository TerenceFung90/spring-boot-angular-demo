package com.example.demo.func.service;

import java.util.List;

import com.example.demo.func.bean.ImageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ImageService {

	List<ImageDTO> findImages(Boolean autoCorrect, Integer pageNumber, Integer pageSize, String q, Boolean safeSearch) throws JsonMappingException, JsonProcessingException;

}
