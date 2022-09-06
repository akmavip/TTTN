package com.tttn.saleweb.service;

public interface IMailService {
	boolean send(String to, String subject, String body, String... others);

}
