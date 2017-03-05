/*******************************************************
*           Copyright (C) 2014 GreenFLOPS              *
*                                                      *
* This file is part of GreenFLOPS projects and can not *
* be copied and/or distributed in any medium or format *
*    without the express permission of GreenFLOPS      *
*                                                      *
*              contact@greenflops.com                  *
*******************************************************/


package com.greenflops.sdl;

import java.io.IOException;
import java.util.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SDLConsumer extends Thread {
	private String bootstrapServer = "";
	private String topic = "";
	private KafkaConsumer<String, String> consumer = null;
	private String tableValue = "";
	private Hashtable<String, Map<String,Object>> tables = new Hashtable<String, Map<String,Object>>();
	private int verbose = 0;
	private boolean running = false;

	public SDLConsumer(String bootstrapServer, String topic){
		this.bootstrapServer = bootstrapServer;
		this.topic = topic;
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServer);
	    	props.put("group.id", "group-1");
	    	props.put("enable.auto.commit", "true");
	    	props.put("auto.commit.interval.ms", "1000");
	    	props.put("auto.offset.reset", "earliest");
	    	props.put("session.timeout.ms", "30000");
	    	props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	    	props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

	    	try {
	    		consumer = new KafkaConsumer<String, String>(props);
	    	}
	    	catch (Exception e) {
	        	e.printStackTrace();
	    	}
		this.running = true;
	}
	
	public void run(){
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> updatedMap = new HashMap<String, Object>();
		try{
			consumer.subscribe(Arrays.asList(topic));
		}
		catch (Exception e) {
	        	e.printStackTrace();
	    	}
	    	while (running) {
         		ConsumerRecords<String, String> records = consumer.poll(100);
         		for (ConsumerRecord<String, String> record : records) {
				try{
					updatedMap = tables.get(record.key());
					map = mapper.readValue(record.value(), new TypeReference<Map<String, Object>>(){});
					if(null == updatedMap){
						if(0 != verbose) System.out.println("updatedMap is null");
						updatedMap = map;
					}
					else{
						for (Map.Entry<String, Object> entry : map.entrySet())
						{
    							if(0 != verbose) System.out.println(entry.getKey());
							updatedMap.put(entry.getKey(), entry.getValue());
						}
					}
					if(0 != verbose) System.out.println(record.key());
					tables.put(record.key(), updatedMap);
				}
				catch (Exception e) {
                        		e.printStackTrace();
                		}
				if(0 != verbose) System.out.println(updatedMap);
        		}
		}
	}

	public String get(String key){
		// Implement search in buffer value coresponding to key
		String jsonString = "";
		ObjectMapper mapper = new ObjectMapper();
		if((null != tables) && (false == tables.isEmpty())) {
			try{
				jsonString = mapper.writeValueAsString(tables.get(key));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(0 != verbose) System.out.println("No tables");
		return jsonString;
	}

	public void finish(){
		this.running = false;
	}
}

