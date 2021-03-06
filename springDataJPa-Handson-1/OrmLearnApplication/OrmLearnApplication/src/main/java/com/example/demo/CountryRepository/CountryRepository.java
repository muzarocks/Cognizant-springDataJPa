package com.example.demo.CountryRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Country;



@Repository
public interface CountryRepository extends JpaRepository<Country,String>{

	
	public List<Country> findByNameContainingOrderByName(String countryPattern);
}
