package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String>{
		List<Country> findAllByContinent(String continent);
		List<Country> findAllByContinentAndPopulationBetween(String continent,long minPopulation,long maxPopulation);
		@Query(value = "select c from Country c where c.continent=:continent") // JPQL
		List<Country> kitalaraGoreAra(String continent);
		@Query(value = "select c from countries c where c.continent=:continent",nativeQuery = true) // SQL
		List<Country> kitalaraGoreSorgula(String continent);
		// Criteria API
}
