/*******************************************************
*           Copyright (C) 2014 GreenFLOPS              *
*                                                      *
* This file is part of GreenFLOPS projects and can not *
* be copied and/or distributed in any medium or format *
*    without the express permission of GreenFLOPS      *
*                                                      *
*              contact@greenflops.com                  *
*******************************************************/
import java.util.Properties;
import java.util.Arrays;
import com.greenflops.sdl.*;


public class SDLActor {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";

	// Server address
	private String address;
	// Tables Topics
	private String UI_NS_VNF_ORCHESTRATION = "UI_NS_VNF_ORCHESTRATION";
	private String MICRO_SERVICES_INFO = "MICRO_SERVICES_INFO";
	// Tables row primaryKey
	private String GUIPrimaryKey = "0";
	private String microserviceId1 = "a00000000000001a";
        private String microserviceId2 = "b00000000000001b";
	// Test duration
	private long testDuration;

    	public static void main(String[] args) throws Exception {
		SDLActor sdl = new SDLActor();
        	if(args.length != 3) {
            		System.out.println("enter [brockerIPAddress | localhost] [set | get] [durationInSeconds | -1 for infinite]");
            		return;
        	}

        	sdl.address = args[0].toString() + ":9092";

		sdl.testDuration = 1000 * Long.parseLong(args[2]);
	
		switch (args[1].toString()) {
			case "set":
				sdl.set();
			break;
			case "get":
			default:
				sdl.get();
			break;
		}
	}

	void set(){
		// Create Producer threads
                SDLProducer GUIProducer = new SDLProducer(address, UI_NS_VNF_ORCHESTRATION);
                SDLProducer SwarmProducer = new SDLProducer(address, MICRO_SERVICES_INFO);
                // Run Producers
                GUIProducer.start();
                SwarmProducer.start();

		String row;
	        long start=System.currentTimeMillis();
                while((0 >= testDuration) || (System.currentTimeMillis() - start < testDuration)){

        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"OpenStackEnabled\":true}";
        	GUIProducer.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"OpenStackEnabled\":false,\"microServiceDeplEnabled\":false}";
		GUIProducer.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);

		try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"OpenStackEnabled\":true,\"microServiceDeplEnabled\":true,\"updatedBy\":\"GUI\"}";
		GUIProducer.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and full row value=" + row + ANSI_RESET);

		try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"microServiceDeplEnabled\":false}";
		GUIProducer.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"OpenStackEnabled\":false}";
		GUIProducer.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"microServiceDeplEnabled\":true}";
		GUIProducer.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);
        
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"RUNNING\",\"startDate\":"+ System.currentTimeMillis() + "}";
		SwarmProducer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);

		try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"STOPPED\"}";
		SwarmProducer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);
                row = "{\"microserviceId2\":\""+microserviceId2+"\",\"status\":\"RUNNING\",\"startDate\":"+ System.currentTimeMillis() + "}";
		SwarmProducer.set(microserviceId2, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId2 + " and partial row value=" + row + ANSI_RESET);

		try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"microServiceOrchProvider\":\"Swarm\",\"microServiceName\":\"BBSC\",\"sourcePortNo\":51345,\"chainingPortRange\":1024,\"sourceIpAddress\":\"172.1.0.15\",\"status\":\"NOT_CREATED\",\"deployedServer\":\"FEU\",\"startDate\":"+ System.currentTimeMillis() + "}";
		SwarmProducer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and full row value=" + row + ANSI_RESET);

		try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"CREATED\"}";
		SwarmProducer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);

               	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"RUNNING\",\"startDate\":"+ System.currentTimeMillis() + "}";
		SwarmProducer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);
                row = "{\"microserviceId2\":\""+microserviceId2+"\",\"status\":\"STOPPED\"}";
		SwarmProducer.set(microserviceId2, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId2 + " and partial row value=" + row + ANSI_RESET);


               	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"STOPPED\"}";
		SwarmProducer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);
                row = "{\"microserviceId2\":\""+microserviceId2+"\",\"status\":\"RUNNING\",\"startDate\":"+ System.currentTimeMillis() + "}";
		SwarmProducer.set(microserviceId2, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId2 + " and partial row value=" + row + ANSI_RESET);	
		}
		                
		// Finish Producers threads
                GUIProducer.finish();
                SwarmProducer.finish();
	}

	void get(){
		// Create Consumers threads
		SDLConsumer GUIConsumer = new SDLConsumer(address, UI_NS_VNF_ORCHESTRATION);
                SDLConsumer SwarmConsumer = new SDLConsumer(address, MICRO_SERVICES_INFO);
		// Run Consumers polling
        	GUIConsumer.start();
		SwarmConsumer.start();
		
		// During a loop, each seconds Get value of a table with its primaryKey
		long start=System.currentTimeMillis();
        	while((0 >= testDuration) || (System.currentTimeMillis() - start < testDuration)){
			try{
				System.out.printf(ANSI_GREEN + "GUIPrimaryKey=%s, row=%s\n" + ANSI_RESET, GUIPrimaryKey, GUIConsumer.get(GUIPrimaryKey));
			}
			catch (Exception e) {
                        	e.printStackTrace();
                	}
                	try{
                        	System.out.printf(ANSI_GREEN + "microserviceId1=%s, row=%s\n" + ANSI_RESET, microserviceId1, SwarmConsumer.get(microserviceId1));
                	}
                	catch (Exception e) {
                        	e.printStackTrace();
                	}

                	try{
                        	System.out.printf(ANSI_GREEN + "microserviceId2=%s, row=%s\n" + ANSI_RESET, microserviceId2, SwarmConsumer.get(microserviceId2));
                	}
                	catch (Exception e) {
                        	e.printStackTrace();
                	}	
			try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
         	}
		// Finish Consumers threads
		GUIConsumer.finish();
		SwarmConsumer.finish();
    	}
}

