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

import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SDLProducer extends Thread {
	private String bootstrapServer = "";
	private String topic = "";
	private boolean running = false;
	private Producer<String, String> producer = null;

	public SDLProducer(String bootstrapServer, String topic){
		this.bootstrapServer = bootstrapServer;
		this.topic = topic;
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServer);
		props.put("acks", "0");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 0);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

	    	try {
			producer = new KafkaProducer<String, String>(props);
	    	}
	    	catch (Exception e) {
	        	e.printStackTrace();
	    	}
		this.running = true;
	}
	
	public void run(){
		while (running) {
			try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
		}
	}

	public void set(String primaryKey, String JsonRow){
		producer.send(new ProducerRecord<String, String>(topic, primaryKey, JsonRow));
	}

	public void finish(){
		this.running = false;
	}
}

