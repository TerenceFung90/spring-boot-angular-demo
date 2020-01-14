package com.example.demo.func.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.func.bean.ImageDTO;
import com.example.demo.func.bean.ImageSearchResponse;
import com.example.demo.func.mapper.ImageMapper;
import com.example.demo.func.service.ImageService;
import com.example.demo.repo.dao.ImageDAO;
import com.example.demo.repo.dao.RequestParamDAO;
import com.example.demo.repo.model.Image;
import com.example.demo.repo.model.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
	
	private ImageDAO imageDAO;
	private RequestParamDAO requestParamDAO;
	
	private ImageMapper imageMapper;
	private RestTemplate restTemplate;
	
	@Override
	@Transactional
	public List<ImageDTO> findImages(Boolean autoCorrect, Integer pageNumber, Integer pageSize, String q, Boolean safeSearch) throws JsonMappingException, JsonProcessingException {
		
		List<Image> list = new ArrayList<>();
		
		RequestParam param = new RequestParam(null, autoCorrect, pageNumber, pageSize, q, safeSearch);
		Example<RequestParam> example = Example.of(param);
		
		// find param to database
		if(requestParamDAO.findOne(example).isPresent()){
			// if found param in database, then take from database
			list = imageDAO.findByCriteriaAndPage(autoCorrect, pageNumber, pageSize, q, safeSearch);
		}else{
			// else save param in database and directly call api for new data.
			requestParamDAO.save(param);
			list = getImageByApi(autoCorrect, pageNumber, pageSize, q, safeSearch);
		}
		
		return imageMapper.toImageDtos(list);
	}

	private List<Image> getImageByApi(Boolean autoCorrect, Integer pageNumber, Integer pageSize, String q,
			Boolean safeSearch) throws JsonMappingException, JsonProcessingException {
		List<Image> list;
		// call api to get data. if no data found, then return
		List<ImageDTO> dtos = getImages(autoCorrect, pageNumber, pageSize, q, safeSearch);
		
		// save data to database
		if(!dtos.isEmpty()){
			List<Image> images = imageMapper.toImages(dtos);
			imageDAO.saveAll(images);
		}
		
		// find in database again.
		list = imageDAO.findByCriteriaAndPage(autoCorrect, pageNumber, pageSize, q, safeSearch);
		return list;
	}
	
	private List<ImageDTO> getImages(Boolean autoCorrect, Integer pageNumber, Integer pageSize, String q,
			Boolean safeSearch) throws JsonMappingException, JsonProcessingException {
		List<ImageDTO> dtos = new ArrayList<>();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("x-rapidapi-host", "contextualwebsearch-websearch-v1.p.rapidapi.com");
			headers.set("x-rapidapi-key", "dd9018addcmshdb788c9f2d27c02p1614a4jsnc7640ca8649a");

			String url = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/Search/ImageSearchAPI";
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
			        .queryParam("autoCorrect", autoCorrect)
			        .queryParam("pageNumber", pageNumber)
			        .queryParam("pageSize", pageSize)
			        .queryParam("q", q)
			        .queryParam("safeSearch", safeSearch);
			
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<String> response = restTemplate.exchange(
			    builder.toUriString(), HttpMethod.GET, entity, String.class);
			
			String responseBody = response.getBody();
			log.info(responseBody);
			
			if(!StringUtils.isEmpty(responseBody)){
				ObjectMapper objectMapper = new ObjectMapper();
				ImageSearchResponse responseObj = objectMapper.readValue(responseBody, ImageSearchResponse.class);
				dtos = responseObj.getValue();
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			// use mock data
			ObjectMapper objectMapper = new ObjectMapper();
			ImageSearchResponse responseObj = null;
			responseObj = objectMapper.readValue(getDummyData(), ImageSearchResponse.class);
			dtos = responseObj.getValue();
		}
		return dtos;
		
	}
	
	private String getDummyData(){
		String data = "{\"_type\":\"images\",\"value\":[{\"url\":\"https://dressupwho.net/i-files/thumb_1/20610-donald-trump-vs-hillary-clinton-70x175.jpg\",\"height\":175,\"width\":70,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=6446573495239113800\",\"thumbnailHeight\":400,\"thumbnailWidth\":160,\"base64Encoding\":null},{\"url\":\"http://games.dressuppink.com/images/donald-trump-vs-hillary-clinton.jpg?t9tad7\",\"height\":60,\"width\":80,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=8187980894059393231\",\"thumbnailHeight\":210,\"thumbnailWidth\":280,\"base64Encoding\":null},{\"url\":\"https://pmcdeadline2.files.wordpress.com/2018/08/donald-trump-2.jpg?w=605\",\"height\":403,\"width\":605,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=6250921748791014870\",\"thumbnailHeight\":173,\"thumbnailWidth\":260,\"base64Encoding\":null},{\"url\":\"http://www.india.com/wp-content/uploads/2018/08/donald-trump.jpg\",\"height\":415,\"width\":700,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=8531529161775551633\",\"thumbnailHeight\":237,\"thumbnailWidth\":400,\"base64Encoding\":null},{\"url\":\"https://cdn.dnaindia.com/sites/default/files/styles/full/public/2018/08/28/723845-660810-trumpdonald-afp-031518.jpg\",\"height\":720,\"width\":1280,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=3621691658002374059\",\"thumbnailHeight\":157,\"thumbnailWidth\":280,\"base64Encoding\":null},{\"url\":\"http://projects.thestar.com/donald-trump-fact-check/images/trumpcheck-02.jpg\",\"height\":391,\"width\":800,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=8672178186432489387\",\"thumbnailHeight\":127,\"thumbnailWidth\":260,\"base64Encoding\":null},{\"url\":\"http://a57.foxnews.com/images.foxnews.com/content/fox-news/entertainment/2018/08/28/snl-star-pete-davidson-recalls-working-with-psycho-donald-trump-and-his-bad-audition-to-portray-him/_jcr_content/par/featured_image/media-0.img.jpg/0/0/1535481722723.jpg?ve=1\",\"height\":720,\"width\":1280,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=7252629321769597480\",\"thumbnailHeight\":191,\"thumbnailWidth\":340,\"base64Encoding\":null},{\"url\":\"https://sportshub.cbsistatic.com/i/r/2018/08/28/c9026af8-8d09-4d1b-9c5a-67e082693152/thumbnail/770x433/d62e243ababa261dfcbd28248ed83980/donald-trump-gianni-infantino.jpg\",\"height\":433,\"width\":770,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=5708997472337713695\",\"thumbnailHeight\":157,\"thumbnailWidth\":280,\"base64Encoding\":null},{\"url\":\"https://images.thestar.com/4683apHlx2GybGKlNQfrEOIzK6w=/1200x799/smart/filters:cb(1534514539753)/https://www.thestar.com/content/dam/thestar/news/world/2018/08/17/donald-trump-cancels-ridiculously-expensive-military-parade/trumpmilitary.jpg\",\"height\":799,\"width\":1200,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=3802727927775564525\",\"thumbnailHeight\":159,\"thumbnailWidth\":240,\"base64Encoding\":null}]}";
		return data;
	}

}
