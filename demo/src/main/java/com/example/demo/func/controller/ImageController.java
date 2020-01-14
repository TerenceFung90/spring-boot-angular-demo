package com.example.demo.func.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.func.bean.ImageDTO;
import com.example.demo.func.service.ImageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class ImageController {

	@Autowired
	ImageService imageService;

	// sample
	// http://localhost:8080/findImages?autoCorrect=true&pageNumber=1&pageSize=10&q=a&safeSearch=false
	@RequestMapping(value = "/findImages", method = RequestMethod.GET)
	public List<ImageDTO> findImages(
			@RequestParam("autoCorrect") Boolean autoCorrect,
			@RequestParam("pageNumber") Integer pageNumber, 
			@RequestParam("pageSize") Integer pageSize,
			@RequestParam("q") String q, 
			@RequestParam("safeSearch") Boolean safeSearch) {

		List<ImageDTO> list = new ArrayList<ImageDTO>();
		try {
			list = imageService.findImages(autoCorrect, pageNumber, pageSize, q, safeSearch);
		} catch (JsonMappingException e) {

			log.error(e.getMessage(), e);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}

}
