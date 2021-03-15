package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.CountryService.CountryService;
import com.example.demo.CountryService.exception.CountryNotFoundException;
import com.example.demo.model.Country;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	
	
	private static void testGetAllCountries()
	{
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}",countries);
		LOGGER.info("End");
	}
	
	
	public static void getAllCountriesTest()
	{
		LOGGER.info("Start");
		try {
		Country country = countryService.findCountryByCode("IN");
		LOGGER.debug("Country:{}",country);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		LOGGER.info("End");
	}
	
	
	public static void testAddCountry()
	{
		LOGGER.info("Start");

		Country country = new Country("AS","American Samoa");
		
		countryService.addCountry(country);
		
		try {
			Country countrytest = countryService.findCountryByCode("AS");
			LOGGER.debug("Country:{}",countrytest);
			}catch(CountryNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		
		LOGGER.info("End");

	}
	
	public static void deleteCountry()
	{
		LOGGER.info("Start");

		try {
			countryService.deleteCountry("AS");
		}catch(CountryNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
		LOGGER.info("End");

	}
	
	
	public static void updateCountry()
	{
		LOGGER.info("Start");

		try {
		countryService.updateCountry("AS","Argentina Samosa");
		}catch(CountryNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
		LOGGER.info("End");

	}
	
	
	public static void getCountryWithPattern()
	{
		LOGGER.info("Start");

		List<Country> countryList = countryService.findCountryByPattern("ou");
		
		for(Country c:countryList)
		{
			System.out.println(c.getCode()+" "+c.getName());
		}
		
		LOGGER.info("End");

	}
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		LOGGER.info("Inside main");
		
		countryService = context.getBean(CountryService.class);
		
		testGetAllCountries();
	
		getAllCountriesTest();
		
		testAddCountry();
		
		updateCountry();
		
		deleteCountry();
		
		
		testGetAllCountries();
		
		getCountryWithPattern();

		
		
		
	}

	
}
