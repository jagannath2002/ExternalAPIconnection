package com.API.API3rdparty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.API.API3rdparty.entity.ApiEntity;
import com.API.API3rdparty.service.ApiService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api2/3rdparty")
public class ApiController {
	
	@Autowired
	private ApiService apiService;
		
	@GetMapping("/calling")
	public List<ApiEntity> apicalling(){
		return this.apiService.apicalling();
	}
	@PostMapping("/post")
	public List<ApiEntity> postData() {
		return this.apiService.postData();
	}
	@PostMapping("/create")
	public ApiEntity createData(@RequestBody ApiEntity apiEntity) {
		return this.apiService.createData(apiEntity);
	}
	@PutMapping("/update/{id}")
	public ApiEntity updateData(@PathVariable Long id, @RequestBody ApiEntity apiEntity) {
		return this.apiService.updateData(id,apiEntity);
	} 
	@DeleteMapping("/delete/{id}")
	public void deleteData(@PathVariable Long id) {
		 this.apiService.deleteData(id);
	} 
	
}