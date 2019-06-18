package com.myspace.kafkasleuth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myspace.kafkasleuth.model.DbpLogModel;
import com.myspace.kafkasleuth.services.ILogService;
import com.myspace.kafkasleuth.services.KafkaProducer;

@RestController
@RequestMapping("/kafkaSleuth")
public class LogProducer {
	private static final Logger log = LoggerFactory.getLogger(LogProducer.class);

	@Autowired
	KafkaProducer producer;
	
	@Autowired
	private ILogService logService;
	
	public LogProducer() {
		
	}
	
	@PostMapping("/logs")
	public void sendLogs(DbpLogModel dbpLogModel) {
		System.out.println(String.format("Internal Server (traceId: %s)", MDC.get("X-B3-TraceId")));
		System.out.println(String.format("Internal Server (spanId: %s)", MDC.get("X-B3-SpanId")));
		logService.logservice(dbpLogModel);
	}
	
}
