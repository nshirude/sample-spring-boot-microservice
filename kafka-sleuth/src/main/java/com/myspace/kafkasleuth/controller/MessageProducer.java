package com.myspace.kafkasleuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myspace.kafkasleuth.services.KafkaProducer;


@RestController
@RequestMapping("/kafkaSleuth")
public class MessageProducer {
	
	@Autowired
	KafkaProducer producer;

	@PostMapping("/kafkaLogs")
	public void sendMessage(String data) {
		producer.send(data);
	}

}
