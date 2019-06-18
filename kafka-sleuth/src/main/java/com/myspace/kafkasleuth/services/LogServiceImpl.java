package com.myspace.kafkasleuth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myspace.kafkasleuth.model.DbpLogModel;

@Service
public class LogServiceImpl implements ILogService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void logservice(DbpLogModel log) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(log);
			logger.info(json);
		} catch (JsonProcessingException ex) {
			logger.info(ex.getStackTrace().toString());
		}
	}
	
}
