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
	private String UI_NS_VNC_SETTING = "UI_NS_VNC_SETTING";
	private String UI_SS_TRAFFIC_PROFILE = "UI_SS_TRAFFIC_PROFILE";
	private String UI_CONTROL_SCREEN_SETTINGS = "UI_CONTROL_SCREEN_SETTINGS";
	private String CELL_VM_INFO = "CELL_VM_INFO";
	private String RESOURCE_INFO = "RESOURCE_INFO";
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
	void UI_NS_VNF_ORCHESTRATION_Set(SDLProducer prod){
		String row;
		GUIPrimaryKey = "0";

        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"OpenStackEnabled\":true}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"OpenStackEnabled\":false,\"microServiceDeplEnabled\":false}";
		prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);

		try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"OpenStackEnabled\":true,\"microServiceDeplEnabled\":true,\"updatedBy\":\"GUI\"}";
		prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and full row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"OpenStackEnabled\":false,\"microServiceDeplEnabled\":false}";
		prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNF_ORCHESTRATION table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);
	}

	void UI_NS_VNC_SETTING_Set(SDLProducer prod){
		String row;
		GUIPrimaryKey = "0";

        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"sliceRouting4G\":\"N1\",\"sliceRouting5G\":\"OFF\"}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNC_SETTING table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"sliceRouting4G\":\"N2\",\"sliceRouting5G\":\"N2\",\"sliceRouting5GIoT\":\"N2\",\"allocatedBandwidth4G\":1,\"allocatedBandwidth5GMBB\":1,\"allocatedBandwidth5GIoT\":1\"microS1Bandwidth\":1,\"microS2Bandwidth\":1,\"updatedBy\":\"GUI\"}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNC_SETTING table with primaryKey=\"0\" and full row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"sliceRouting4G\":\"OFF\",\"sliceRouting5G\":\"N2\"}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_NS_VNC_SETTING table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);
	}

	void UI_SS_TRAFFIC_PROFILE_Set(SDLProducer prod){
		String row;
		GUIPrimaryKey = "0";

        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"MBBTrafficProfileMicroS1\":\"LOW\",\"MBBTrafficProfileMicroS2\":\"LOW\"}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_SS_TRAFFIC_PROFILE table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"videoTrafficEnabled\":true,\"gamingTrafficEnabled\":true,\"MBBTrafficMicroS1Enabled\":true,\"MBBTrafficMicroS2Enabled\":true,\"MBBTrafficProfileMicroS1\":\"MEDIUM\",\"MBBTrafficProfileMicroS2\":\"MEDIUM\",\"IoTTrafficEnabled\":true,\"IoTTrafficSensorCount\":5,\"IoTTrafficProfile\":\"Uniform\",\"MHNetworkDelay\":5,\"gamingSplitEnabled\":true,\"videoSplitEnabled\":true,\"ECMigrationEnabled\":true,\"IoTSPBCount\":5,\"updatedBy\":\"GUI\"}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_SS_TRAFFIC_PROFILE table with primaryKey=\"0\" and full row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"MBBTrafficProfileMicroS1\":\"HIGH\",\"MBBTrafficProfileMicroS2\":\"HIGH\"}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_SS_TRAFFIC_PROFILE table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);
	}

	void UI_CONTROL_SCREEN_SETTINGS_Set(SDLProducer prod){
		String row;
		GUIPrimaryKey = "0";

        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"processingThresholdFEU\":0,\"processingThresholdxUP1\":0}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_CONTROL_SCREEN_SETTINGS table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"processingThresholdFEU\":50,\"processingThresholdxUP1\":50,\"processingThresholdxUP2\":50,\"microS1BitRatioThresholdGBR\":50,\"microS2BitRatioThresholdGBR\":50,\"microS1BitRatioThresholdNGBR\":50,\"microS2BitRatioThresholdNGBR\":50,\"updatedBy\":\"GUI\"}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_CONTROL_SCREEN_SETTINGS table with primaryKey=\"0\" and full row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"processingThresholdFEU\":90,\"processingThresholdxUP1\":90}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI UI_CONTROL_SCREEN_SETTINGS table with primaryKey=\"0\" and partial row value=" + row + ANSI_RESET);
	}

	void CELL_VM_INFO_Set(SDLProducer prod){
		String row;
		GUIPrimaryKey = "vran-cell1-6";

        	row = "{\"cellVmHostName\":{\"vran-cell1-6\":[{\"fzmName\":\"FZM1\",\"numConnectedUE\":0,\"lastUpdateDate\":" + System.currentTimeMillis() + "}]}}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI CELL_VM_INFO table with primaryKey=" + GUIPrimaryKey + "and partial row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"cellVmHostName\":{\"vran-cell1-6\":[{\"fzmName\":\"FZM1\",\"numConnectedUE\":10,\"meanAppLatency\":1.5,\"meanAggThroughput\":1.0,\"poolUtilPercentage\":10,\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"updatedBy\":\"GUI\"},{\"fzmName\":\"FZM2\",\"numConnectedUE\":20,\"meanAppLatency\":2.5,\"meanAggThroughput\":20.0,\"poolUtilPercentage\":20,\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"updatedBy\":\"GUI\"},{\"fzmName\":\"FZM3\",\"numConnectedUE\":30,\"meanAppLatency\":3.5,\"meanAggThroughput\":30.0,\"poolUtilPercentage\":30,\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"updatedBy\":\"GUI\"},{\"fzmName\":\"FZM4\",\"numConnectedUE\":40,\"meanAppLatency\":4.5,\"meanAggThroughput\":40.0,\"poolUtilPercentage\":40,\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"updatedBy\":\"GUI\"},{\"fzmName\":\"FZM5\",\"numConnectedUE\":50,\"meanAppLatency\":5.5,\"meanAggThroughput\":50.0,\"poolUtilPercentage\":50,\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"updatedBy\":\"GUI\"},{\"fzmName\":\"FZM6\",\"numConnectedUE\":60,\"meanAppLatency\":6.5,\"meanAggThroughput\":60.0,\"poolUtilPercentage\":60,\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"updatedBy\":\"GUI\"}]}}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI CELL_VM_INFO table with primaryKey=" + GUIPrimaryKey + "and full row value=" + row + ANSI_RESET);

        	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
        	row = "{\"cellVmHostName\":{\"vran-cell1-6\":[{\"fzmName\":\"FZM1\",\"numConnectedUE\":80,\"lastUpdateDate\":" + System.currentTimeMillis() + "}]}}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI CELL_VM_INFO table with primaryKey=" + GUIPrimaryKey + "and partial row value=" + row + ANSI_RESET);
	}

	void RESOURCE_INFO_Set(SDLProducer prod){
		String row;
		GUIPrimaryKey = "vran-cell1-6";

		row = "{\"cellVmHostName\":\"vran-cell1-6\",\"r1ConsumptionInPercentage\":10}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI RESOURCE_INFO table with primaryKey=" + GUIPrimaryKey + "and partial row value=" + row + ANSI_RESET);
		
		row = "{\"cellVmHostName\":\"vran-cell1-6\",\"r1ConsumptionInPercentage\":40,\"r2ConsumptionInPercentage\":40,\"r3ConsumptionInPercentage\":40,\"r4ConsumptionInPercentage\":40,\"r5ConsumptionInPercentage\":40,\"r6ConsumptionInPercentage\":40,\"r7ConsumptionInPercentage\":40,\"r8ConsumptionInPercentage\":40,\"r9ConsumptionInPercentage\":40,\"lastUpdateDate\":" + System.currentTimeMillis() + ",\"updatedBy\":\"GUI\"}";
        	prod.set(GUIPrimaryKey, row);
        	System.out.println(ANSI_BLUE + "Sent GUI RESOURCE_INFO table with primaryKey=" + GUIPrimaryKey + "and partial row value=" + row + ANSI_RESET);

	}

	void set(){
		// Create Producer threads
                SDLProducer UI_NS_VNF_ORCHESTRATION_Producer = new SDLProducer(address, UI_NS_VNF_ORCHESTRATION);
                SDLProducer UI_NS_VNC_SETTING_Producer = new SDLProducer(address, UI_NS_VNC_SETTING);
		SDLProducer UI_SS_TRAFFIC_PROFILE_Producer = new SDLProducer(address, UI_SS_TRAFFIC_PROFILE);
		SDLProducer UI_CONTROL_SCREEN_SETTINGS_Producer = new SDLProducer(address, UI_CONTROL_SCREEN_SETTINGS);
		SDLProducer CELL_VM_INFO_Producer = new SDLProducer(address, CELL_VM_INFO);
		SDLProducer RESOURCE_INFO_Producer = new SDLProducer(address, RESOURCE_INFO);
                SDLProducer MICRO_SERVICES_INFO_Producer = new SDLProducer(address, MICRO_SERVICES_INFO);

                // Run Producers
                UI_NS_VNF_ORCHESTRATION_Producer.start();
		UI_NS_VNC_SETTING_Producer.start();
		UI_SS_TRAFFIC_PROFILE_Producer.start();
		UI_CONTROL_SCREEN_SETTINGS_Producer.start();
		CELL_VM_INFO_Producer.start();
		RESOURCE_INFO_Producer.start();
                MICRO_SERVICES_INFO_Producer.start();

		String row;
	        long start=System.currentTimeMillis();
                while((0 >= testDuration) || (System.currentTimeMillis() - start < testDuration)){
			UI_NS_VNF_ORCHESTRATION_Set(UI_NS_VNF_ORCHESTRATION_Producer);
			UI_NS_VNC_SETTING_Set(UI_NS_VNC_SETTING_Producer);
			UI_SS_TRAFFIC_PROFILE_Set(UI_SS_TRAFFIC_PROFILE_Producer);
			UI_CONTROL_SCREEN_SETTINGS_Set(UI_CONTROL_SCREEN_SETTINGS_Producer);
			CELL_VM_INFO_Set(CELL_VM_INFO_Producer);
			RESOURCE_INFO_Set(RESOURCE_INFO_Producer);

        
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"RUNNING\",\"startDate\":"+ System.currentTimeMillis() + "}";
		MICRO_SERVICES_INFO_Producer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);

		try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"STOPPED\"}";
		MICRO_SERVICES_INFO_Producer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);
                row = "{\"microserviceId2\":\""+microserviceId2+"\",\"status\":\"RUNNING\",\"startDate\":"+ System.currentTimeMillis() + "}";
		MICRO_SERVICES_INFO_Producer.set(microserviceId2, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId2 + " and partial row value=" + row + ANSI_RESET);

		try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"microServiceOrchProvider\":\"Swarm\",\"microServiceName\":\"BBSC\",\"sourcePortNo\":51345,\"chainingPortRange\":1024,\"sourceIpAddress\":\"172.1.0.15\",\"status\":\"NOT_CREATED\",\"deployedServer\":\"FEU\",\"startDate\":"+ System.currentTimeMillis() + "}";
		MICRO_SERVICES_INFO_Producer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and full row value=" + row + ANSI_RESET);

		try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"CREATED\"}";
		MICRO_SERVICES_INFO_Producer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);

               	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"RUNNING\",\"startDate\":"+ System.currentTimeMillis() + "}";
		MICRO_SERVICES_INFO_Producer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);
                row = "{\"microserviceId2\":\""+microserviceId2+"\",\"status\":\"STOPPED\"}";
		MICRO_SERVICES_INFO_Producer.set(microserviceId2, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId2 + " and partial row value=" + row + ANSI_RESET);


               	try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
                row = "{\"microserviceId1\":\""+microserviceId1+"\",\"status\":\"STOPPED\"}";
		MICRO_SERVICES_INFO_Producer.set(microserviceId1, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId1 + " and partial row value=" + row + ANSI_RESET);
                row = "{\"microserviceId2\":\""+microserviceId2+"\",\"status\":\"RUNNING\",\"startDate\":"+ System.currentTimeMillis() + "}";
		MICRO_SERVICES_INFO_Producer.set(microserviceId2, row);
                System.out.println(ANSI_BLUE + "Sent Swarm MICRO_SERVICES_INFO table with primaryKey="+ microserviceId2 + " and partial row value=" + row + ANSI_RESET);	
		}

		// Finish Producers threads
                UI_NS_VNF_ORCHESTRATION_Producer.finish();
                MICRO_SERVICES_INFO_Producer.finish();
	}

	void get(){
		// Create Consumers threads
		SDLConsumer UI_NS_VNF_ORCHESTRATION_Consumer = new SDLConsumer(address, UI_NS_VNF_ORCHESTRATION);
                SDLConsumer MICRO_SERVICES_INFO_Consumer = new SDLConsumer(address, MICRO_SERVICES_INFO);
		// Run Consumers polling
        	UI_NS_VNF_ORCHESTRATION_Consumer.start();
		MICRO_SERVICES_INFO_Consumer.start();
		
		// During a loop, each seconds Get value of a table with its primaryKey
		long start=System.currentTimeMillis();
        	while((0 >= testDuration) || (System.currentTimeMillis() - start < testDuration)){
			try{
				System.out.printf(ANSI_GREEN + "GUIPrimaryKey=%s, row=%s\n" + ANSI_RESET, GUIPrimaryKey, UI_NS_VNF_ORCHESTRATION_Consumer.get(GUIPrimaryKey));
			}
			catch (Exception e) {
                        	e.printStackTrace();
                	}
                	try{
                        	System.out.printf(ANSI_GREEN + "microserviceId1=%s, row=%s\n" + ANSI_RESET, microserviceId1, MICRO_SERVICES_INFO_Consumer.get(microserviceId1));
                	}
                	catch (Exception e) {
                        	e.printStackTrace();
                	}

                	try{
                        	System.out.printf(ANSI_GREEN + "microserviceId2=%s, row=%s\n" + ANSI_RESET, microserviceId2, MICRO_SERVICES_INFO_Consumer.get(microserviceId2));
                	}
                	catch (Exception e) {
                        	e.printStackTrace();
                	}	
			try{Thread.sleep(1000);}catch(Exception e) {e.printStackTrace();}
         	}
		// Finish Consumers threads
		UI_NS_VNF_ORCHESTRATION_Consumer.finish();
		MICRO_SERVICES_INFO_Consumer.finish();
    	}
}

