package com.example.demo.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.repo.model.Image;

@Repository
public interface ImageDAO extends JpaRepository<Image, Long>, ImageDAOCustom {


}
