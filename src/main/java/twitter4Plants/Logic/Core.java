package twitter4Plants.Logic;

import twitter4Plants.DAO.*;
import twitter4Plants.Plants.PlantCheck;
import twitter4Plants.Plants.PlantMeta;
import twitter4Plants.Plants.PlantStatus;
import twitter4Plants.Plants.PlantType;
import twitter4Plants.TwitterSender.Sender;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class Core {

    private Sender twitterSender;
    private SensorDAO sensordao;
    private ServerDAO serverdao;
    private Timer timer;
    private List<PlantMeta> plantList;
    private int plantUpdateMins;

    Core(int plantUpdateMins, String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {

        this.plantUpdateMins = plantUpdateMins;

        this.twitterSender = Sender.getInstance(consumerKey, consumerSecret, accessToken, tokenSecret);

        this.sensordao = new DummySensorDAO();
        this.serverdao = new ServerDAOImpl();
    }

    public void run() {    	  	
    	
        twitterSender.connect();
        
        updateMetas();
        
        
        timer = new Timer(plantUpdateMins, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	updatePlants();
            }
        });

        timer.start();
        
        command_terminal();
        
    }
    
    private void updatePlants(){
    	Iterator<PlantMeta> itr = plantList.iterator();
    	
    	while(itr.hasNext()){
        	try{
        		sendUpdate(itr.next());
        	}
        	catch(Exception ex){
        		System.err.println("Maybe that plant doesn' exist");
        		ex.printStackTrace();
        	}
    	}	
    }
    
    private void updateMetas(){
        plantList = serverdao.getAllMetas();
    	Iterator<PlantMeta> itr = plantList.iterator();
    	System.out.print("Loaded Plants: ");
    	while(itr.hasNext()){
        	try{
        		System.out.print(itr.next().getPlantId() + " ");
        	}
        	catch(Exception ex){
        		System.err.println("Maybe that plant doesn' exist");
        		ex.printStackTrace();
        	}
    	}
    	System.out.println();
    }
    
    
    private void sendUpdate(PlantMeta plantMeta){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String today = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(time);
        PlantStatus plantStatus = sensordao.getPlantStatus(plantMeta.getPlantId());
        PlantType plantType = serverdao.getPlantType(plantMeta.getTypeId());
        PlantCheck plantCheck = new PlantCheck(plantStatus, plantType);
        
        String messageTemp = "#".concat(plantMeta.getPlantName())
                .concat(" says: ").concat(plantCheck.temperatureStatus())
                .concat(" @").concat(plantMeta.getOwnerTwitter())
                .concat(" ").concat(today)
                .concat(" #twoc15");
        twitterSender.toTweet(messageTemp);
        String messageHumidity = "#".concat(plantMeta.getPlantName())
                .concat(" says: ").concat(plantCheck.humidityStatus())
                .concat(" @").concat(plantMeta.getOwnerTwitter())
                .concat(" ").concat(today)
                .concat(" #twoc15");
        twitterSender.toTweet(messageHumidity);
        String messageLight = "#".concat(plantMeta.getPlantName())
                .concat(" says: ").concat(plantCheck.lightStatus())
                .concat(" @").concat(plantMeta.getOwnerTwitter())
                .concat(" ").concat(today)
                .concat(" #twoc15");
        twitterSender.toTweet(messageLight);
    }
    
    private void command_terminal(){
    	boolean salir = false;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        String command = null;
        PlantMeta meta;
        PlantType type;
        String plantName, ownerTwitter;
        int plantID, typeID;
        double humidityMax, humidityMin, humidityHappy;
        double temperatureMax, temperatureMin, temperatureHappy;
        double lightMax, lightMin, lightHappy;
        
    	
        while(!salir){
	        try {
        		command = br.readLine();
	        		
		        switch(command){
		        case "help":
		        	System.out.println("Commands: quit, forceUpdate, updateMetas, changeTime, addPlant, addPlantType");
		        	break;
		        case "quit":
		        	salir = true;
		        	break;
		        case "forceUpdate":
	        		timer.stop();
	        		updatePlants();
	        		timer.start();
	        		break;
		        case "updateMetas":
		        	timer.stop();
		        	updateMetas();
		        	timer.start();
		        	break;
		        case "changeTime":
		        	System.out.print("Minutes: ");
		        	plantUpdateMins = Integer.parseInt(br.readLine()) * 60000;
		        	timer.stop();
		            timer = new Timer(plantUpdateMins, new ActionListener(){
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                	updatePlants();
		                }
		            });
	        		System.out.println("changed update time to: " + plantUpdateMins/60000);
	        		timer.start();
	        		break;
	        	case "addPlant":
	        		System.out.print("plant ID: ");
	        		plantID = Integer.parseInt(br.readLine());
	        		System.out.print("type ID: ");
	        		typeID = Integer.parseInt(br.readLine());
	        		System.out.print("plant name: ");
	        		plantName = br.readLine();
	        		System.out.print("owner twitter: ");
	        		ownerTwitter = br.readLine();
	        		meta = new PlantMeta(plantID, typeID, plantName, ownerTwitter);
	        		timer.stop();
	        		serverdao.savePlantMeta(meta);
	        		meta = serverdao.getPlantMeta(plantID);
	        		System.out.println("Saved plant:" + meta.getPlantId() + " " + meta.getOwnerTwitter());
	        		timer.start();
	        		break;
	        	case "addPlantType":
	        		System.out.print("Type ID: ");
	        		typeID = Integer.parseInt(br.readLine());
	        		System.out.print("Humidity Max: ");
	        		humidityMax = Double.parseDouble(br.readLine());
	        		System.out.print("Humidity Min: ");
	        		humidityMin = Double.parseDouble(br.readLine());
	        		System.out.print("Humidity Happy: ");
	        		humidityHappy = Double.parseDouble(br.readLine());
	        		System.out.print("Temperature Max: ");
	        		temperatureMax = Double.parseDouble(br.readLine());
	        		System.out.print("Temperature Min: ");
	        		temperatureMin = Double.parseDouble(br.readLine());
	        		System.out.print("Temperature Happy: ");
	        		temperatureHappy = Double.parseDouble(br.readLine());
	        		System.out.print("Light Max: ");
	        		lightMax = Double.parseDouble(br.readLine());
	        		System.out.print("Light Min: ");
	        		lightMin = Double.parseDouble(br.readLine());
	        		System.out.print("Light Happy: ");
	        		lightHappy = Double.parseDouble(br.readLine());  
	        		type = new PlantType(typeID, temperatureMax, temperatureMin, temperatureHappy,
	        				humidityMax, humidityMin, humidityHappy,
	        				lightMax, lightMin, lightHappy);
	        		timer.stop();
	        		serverdao.savePlantType(type);
	        		type = serverdao.getPlantType(typeID);
	        		System.out.println("Saved type:" + type.getId());
	        		timer.start();
	        		break;
	        	default:
	        		System.out.println(command.concat(" Not an existent command."));
		        }
				
			} catch (IOException e1) {
				System.out.println("Console is not working.");
				e1.printStackTrace();
			} catch(NumberFormatException e){
				System.out.println("A param is not correct.");
				e.printStackTrace();
			} catch(Exception e){
				System.out.println("Unknown exception.");
				e.printStackTrace();			
			}
    
        }
    }
}
