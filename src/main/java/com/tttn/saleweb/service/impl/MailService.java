package com.tttn.saleweb.service.impl;
import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tttn.saleweb.service.IMailService;


@Service
public class MailService implements IMailService {
	@Autowired
	JavaMailSender sender;
	public boolean send(String to, String subject, String body, String... others) {
		try {
			MimeMessage mail = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			// Người gửi
			String from = "VBOOK ACCOUNT HELP <quanghuy22061997@gmail.com>";
			if (others.length > 0 && others[0] != null) {
				from = String.format("%s <%s>", others[0], others[0]);
			}
			helper.setReplyTo(from);
			helper.setFrom(from);
			// Send
			sender.send(mail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
