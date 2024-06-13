package com.API.API3rdparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.API3rdparty.entity.ApiEntity;

public interface ApiRepository extends JpaRepository<ApiEntity,Long> {
	
}
