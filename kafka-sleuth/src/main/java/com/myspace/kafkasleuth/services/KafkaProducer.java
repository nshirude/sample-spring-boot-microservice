package com.myspace.kafkasleuth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducer {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${jsa.kafka.topic}")
	String kafkaTopic = "billing_service";
	
	public void send(String data) {
	    /*log.info("sending data='{}'", "{'key':'somekey','words':['word22']}");
	    
	    ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaTopic, "{'key':'somekey','words':['word22']}");
	    
	    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("success ; data:"+ data +"; Topic:" + result.getRecordMetadata().topic());
				log.debug("BANKING - SENT DETAILS SUCCESSFULLY; data:"+ "{'key':'somekey','words':['word22']}" +"; Topic:" + result.getRecordMetadata().topic() + "; Partition:" + result.getRecordMetadata().partition() + "; Offset:" + result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("failed "+ data);
			}
		});*/
	}
}
