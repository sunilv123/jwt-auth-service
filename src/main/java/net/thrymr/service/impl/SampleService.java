package net.thrymr.service.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.thrymr.model.Country;
import net.thrymr.repository.CountryRepository;

@Service
public class SampleService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CountryRepository countryRepository;
	
	
	public String saveCountryList() {
		
		String url = "https://restcountries.eu/rest/v2/all";
		
		String object = restTemplate.getForObject(url, String.class);
		
		if(object != null) {
			
			JSONArray jsonArray = new JSONArray(object);
			
			for (int i = 0; i < jsonArray.length(); i++) {
				
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				
				//System.out.println(i+" ------ "+jsonObject.toString());
				
				Country country = new Country();
				
				country.setName(jsonObject.getString("name"));
				country.setCallingCode(jsonObject.getJSONArray("callingCodes").getString(0));
				country.setFlag(jsonObject.getString("flag"));
				if(! jsonObject.isNull("numericCode")) {
					country.setNumericCode(jsonObject.getString("numericCode"));
				}
				
				JSONObject currencyObject = jsonObject.getJSONArray("currencies").getJSONObject(0);
				if(!currencyObject.has("code") ||  currencyObject.isNull("code")) {
					currencyObject = jsonObject.getJSONArray("currencies").getJSONObject(1);
				}
				country.setCurrencyCode(currencyObject.getString("code"));
				country.setCurrencyName(currencyObject.getString("name"));
				if(! currencyObject.isNull("symbol")) {
					country.setCurrencySymbol(currencyObject.getString("symbol"));
				}
				
				countryRepository.save(country);
				
			}
			
		}
		
		return "SUCCESS";
	}


	public List<Country> getCountryList() {
	
		return countryRepository.findAll();
	}
}
