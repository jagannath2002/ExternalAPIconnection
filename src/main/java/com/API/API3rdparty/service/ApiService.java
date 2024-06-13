package com.API.API3rdparty.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.API.API3rdparty.entity.ApiEntity;
import com.API.API3rdparty.repository.ApiRepository;

@Service
public class ApiService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ApiRepository apiRepository;
	String url="https://6669747f2e964a6dfed53557.mockapi.io/api/school/student";

	public List<ApiEntity> apicalling(){
		ResponseEntity<ApiEntity[]> responce=restTemplate.getForEntity(url,ApiEntity[].class);
		return Arrays.asList(responce.getBody());
	}
	public List<ApiEntity> postData() {
		return apiRepository.saveAll(apicalling());
	}
	public ApiEntity createData(ApiEntity apiEntity) {
		ResponseEntity<ApiEntity> responce=restTemplate.postForEntity(url,apiEntity,ApiEntity.class);
		if(responce.getStatusCode()==HttpStatus.OK) {
			this.apiRepository.save(apiEntity);
		}
		return apiEntity;
	}
	public ApiEntity updateData(Long id, ApiEntity apiEntity) {
		ResponseEntity<ApiEntity> responce=restTemplate.exchange(url,HttpMethod.PUT,new HttpEntity<>(apiEntity),ApiEntity.class);
		if(responce.getStatusCode()==HttpStatus.OK) {
			Optional<ApiEntity>ApiData=this.apiRepository.findById(id);
			if(ApiData.isPresent()) {
				ApiEntity Data=ApiData.get();
				if(Data.getName()!=null) {
					Data.setName(apiEntity.getName());
				}
				if(Data.getEmail()!=null) {
					Data.setEmail(apiEntity.getEmail());
				}
                if(Data.getAddress()!=null) {
                	Data.setAddress(apiEntity.getAddress());
				}
			}
		}
		return apiEntity;
	}
	public void deleteData(Long id) {
		ResponseEntity<ApiEntity> responce=restTemplate.exchange(url,HttpMethod.DELETE,HttpEntity.EMPTY,ApiEntity.class);
		if(responce.getStatusCode()==HttpStatus.OK) {
			this.apiRepository.deleteById(id);
		}
	}
	

}
