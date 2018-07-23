package net.thrymr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sunil.bean.AppConstants;
import net.sunil.bean.GenericResponse;
import net.thrymr.service.impl.SampleService;

@RestController
@CrossOrigin
@RequestMapping("/sample")
public class SampleController {

	@Autowired
	private SampleService sampleService;
	
	@GetMapping("/save-country")
	public String saveCountry() {
		return sampleService.saveCountryList();
	}
	
	@GetMapping("/get-country")
	public GenericResponse getCountry() {
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, sampleService.getCountryList());
	}
}
