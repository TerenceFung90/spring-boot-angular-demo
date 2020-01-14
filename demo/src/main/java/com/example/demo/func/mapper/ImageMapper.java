package com.example.demo.func.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.demo.func.bean.ImageDTO;
import com.example.demo.repo.model.Image;

@Mapper(unmappedTargetPolicy=ReportingPolicy.IGNORE, componentModel = "spring")
public interface ImageMapper {
	public static final ImageMapper INSTANCE = Mappers.getMapper( ImageMapper.class );

	Image toImage(ImageDTO dto);
	ImageDTO toImageDto(Image domain);
	
	List<Image> toImages(List<ImageDTO> dtos);
	List<ImageDTO> toImageDtos(List<Image> domains);
}
