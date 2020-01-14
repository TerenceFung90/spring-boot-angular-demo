package com.example.demo.func.bean;

import java.util.List;

import lombok.Data;

@Data
public class ImageSearchResponse {

	private String _type;
	
	private List<ImageDTO> value;
}
