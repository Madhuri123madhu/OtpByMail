package com.motivity.registration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motivity.registration.model.HomeLoanRegistration;
import com.motivity.registration.model.MessageRequest;
import com.motivity.registration.model.OtpBody;
import com.motivity.registration.repository.HomeLoanRegistrationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OtpGenerationService 
{
	
	Logger log=LoggerFactory.getLogger(OtpGenerationService.class);
	
	@Autowired
	private HomeLoanRegistrationRepository homeLoanRegistrationRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	public String otpGeneration(HomeLoanRegistration homeLoanRegistration) {
		   int min=100000;
	       int max=999999;
	       int number=(int)(Math.random()*(max-min+1)+min);	
	       OtpBody.setOtp(String.valueOf(number));
	       
	       MessageRequest messageRequest = new MessageRequest();
	       messageRequest.setToEmail(homeLoanRegistration.getEmail());
	       String bodyMessage = "Your otp is "+number;
	       String subject = "OTP verification";
	       messageRequest.setBody(bodyMessage);
	       messageRequest.setSubject(subject);
	       
	       emailSenderService.sendEmail(messageRequest);
	       homeLoanRegistrationRepository.save(homeLoanRegistration);
	       return "OTP is successfully sent to "+homeLoanRegistration.getEmail();
	}
	
	public String verifiedUserDataSaving() {
		HomeLoanRegistration unverifiedUserLoan = homeLoanRegistrationRepository.findByIsVerified();
		
		log.info("Otp is successfully we save data and updated verified status");
		homeLoanRegistrationRepository.save(unverifiedUserLoan);
		return "success";
	}
	
	public String unverifiedUserDataDeleting() {
		HomeLoanRegistration unverifiedUserLoan = homeLoanRegistrationRepository.findByIsVerified();
		homeLoanRegistrationRepository.delete(unverifiedUserLoan);
		log.info("Otp is unsuccessfull we are deleting data");
		return "Invalid otp";
	}

}
