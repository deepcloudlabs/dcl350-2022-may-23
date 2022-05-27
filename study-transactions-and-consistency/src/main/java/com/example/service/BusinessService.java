package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Country;
import com.example.repository.CountryRepository;

@Service
public class BusinessService {
	@Autowired
	private CountryRepository countryRepository;

	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.MANDATORY)
	public void doBusiness() {

	}

	@Transactional(propagation = Propagation.MANDATORY)
	public Country findCountryByCode(String code) {
		return countryRepository.findById(code)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find the country"));
	}
}
