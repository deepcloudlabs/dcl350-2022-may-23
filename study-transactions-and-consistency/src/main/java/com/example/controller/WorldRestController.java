package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.entity.Country;
import com.example.service.BusinessService;

@RestController
@RequestMapping("countries")
@RequestScope
@CrossOrigin
public class WorldRestController {
	@Autowired
	private BusinessService businessService;
	
	@GetMapping("{code}")
	@Transactional
	public Country getCountryByCode(@PathVariable String code) {
		return businessService.findCountryByCode(code);
	}
}
