package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

	void sendConfirmationEmail(User obj);
	
	void sendEmail(SimpleMailMessage msg);
}
