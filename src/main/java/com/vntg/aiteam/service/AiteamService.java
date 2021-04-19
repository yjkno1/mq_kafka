package com.vntg.aiteam.service;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AiteamService {
	
	@Value("${aiteam.mongodb.collection.name}")
	String collection;
	
	@Autowired
	MongoTemplate mongoTemplate;

	public void addPlcData(JSONArray data) {
		log.debug("Mongo Collection :::: "+collection);
		mongoTemplate.insert(data, collection);
	}
	
}
