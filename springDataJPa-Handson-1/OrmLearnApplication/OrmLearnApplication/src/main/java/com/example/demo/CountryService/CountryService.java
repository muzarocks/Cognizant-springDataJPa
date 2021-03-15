package com.example.demo.CountryService;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.CountryRepository.CountryRepository;
import com.example.demo.CountryService.exception.CountryNotFoundException;
import com.example.demo.model.Country;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Transactional
	public List<Country> getAllCountries()
	{
		return countryRepository.findAll();
	}
	
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException
	{
		Optional<Country> result = countryRepository.findById(countryCode);
		
		if(!result.isPresent())
		{
			throw new CountryNotFoundException("country not found exception has occured");
		}
		
		Country country = result.get();
		
		return country;
		
	}
	
	@Transactional
	public void addCountry(Country country)
	{
		countryRepository.save(country);
	}
	
	public void updateCountry(String countryCode, String countryName) throws CountryNotFoundException
	{
		Optional<Country> result = countryRepository.findById(countryCode);
		
		if(!result.isPresent())
		{
			throw new CountryNotFoundException("country not found exception has occured");
		}
		
		
		Country country = result.get();
		
		country.setName(countryName);
		
		countryRepository.save(country);
	}
	
	@Transactional
	public void deleteCountry(String countryCode) throws CountryNotFoundException
	{
		Optional<Country> result = countryRepository.findById(countryCode);
		
		if(!result.isPresent())
		{
			throw new CountryNotFoundException("country not found exception has occured");
		}
		
		
		//Country country = result.get();
		
		countryRepository.deleteById(countryCode);
		
	}
	
	
	@Transactional
	public List<Country> findCountryByPattern(String countryPattern)
	{
		List<Country> countryList = countryRepository.findByNameContainingOrderByName(countryPattern);
		
		return countryList;
	}
}
