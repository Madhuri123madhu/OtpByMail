package com.motivity.registration.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.motivity.registration.model.MessageRequest;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
@Service
public class EmailSenderService
{
	
	Logger log=LoggerFactory.getLogger(EmailSenderService.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String senderMail;
	
	public void sendEmail(MessageRequest messageRequest) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(senderMail);
		mailMessage.setTo(messageRequest.getToEmail());
		mailMessage.setSubject(messageRequest.getSubject());
		mailMessage.setText(messageRequest.getBody());
		
		mailSender.send(mailMessage);
		log.info("Mail is successfully sent");
		
	}
	
}
