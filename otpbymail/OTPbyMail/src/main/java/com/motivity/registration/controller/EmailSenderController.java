package com.motivity.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motivity.registration.model.HomeLoanRegistration;
import com.motivity.registration.model.OtpBody;
import com.motivity.registration.service.OtpGenerationService;

@RestController
@RequestMapping("/otp")
public class EmailSenderController {
	
	@Autowired
	private OtpGenerationService generationService;
	
	@PostMapping("/sendMail")
	public String generateOtp(@RequestBody HomeLoanRegistration homeLoanRegistration) {
		return generationService.otpGeneration(homeLoanRegistration);
	}
	
	@PostMapping("/verify")
	public String verifyOtp(@RequestParam String otp) {
	    String storedOtp = OtpBody.getOtp();

	    if (storedOtp != null && storedOtp.equals(otp)) {
	        return generationService.verifiedUserDataSaving();
	    } else {
	        return generationService.unverifiedUserDataDeleting();
	    }
	}

}
