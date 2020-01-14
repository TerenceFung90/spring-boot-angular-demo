package com.example.demo.func.bean;

import lombok.Data;

@Data
public class ImageDTO {
	private String url;
	private Integer height;
	private Integer width;
	private String thumbnail;
	private Integer thumbnailHeight;
	private Integer thumbnailWidth;
	private String base64Encoding;
}
