package twitter4Plants.Logic;

import twitter4Plants.DAO.DummySensorDAO;
import twitter4Plants.DAO.SensorDAO;
import twitter4Plants.DAO.ServerDAO;
import twitter4Plants.DAO.ServerDAOImpl;
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

public class Core {

    private Sender twitterSender;
    private SensorDAO sensordao;
    private ServerDAO serverdao;
    private Timer timer;
    private int plantIdToUpdate;

    private int plantUpdateMins;

    Core(int plantUpdateMins, int plantID, String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {

        this.plantUpdateMins = plantUpdateMins;

        this.plantIdToUpdate = plantID;
        this.twitterSender = Sender.getInstance(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.sensordao = new DummySensorDAO();
        this.serverdao = new ServerDAOImpl();
    }

    public void run() {


        //TODO: mantener la lista de plantas a actualizar
        // - La lee de serverdao

        // La operacion del timer será:
        // - leer la información de sensordao y serverdao
        // - comprobar los parámetros
        // - postear en twitter los resultados
    	  	
    	
        twitterSender.connect();
        timer = new Timer(plantUpdateMins, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
	                Timestamp time = new Timestamp(System.currentTimeMillis());
	                PlantStatus plantStatus = sensordao.getPlantStatus(plantIdToUpdate);
	                PlantMeta plantMeta = serverdao.getPlantMeta(plantIdToUpdate);
	                PlantType plantType = serverdao.getPlantType(plantMeta.getTypeId());
	                PlantCheck plantCheck = new PlantCheck(plantStatus, plantType);
	                String messageTemp = "#".concat(plantMeta.getPlantName())
	                        .concat(" says: ").concat(plantCheck.temperatureStatus())
	                        .concat(" @").concat(plantMeta.getOwnerTwitter()).concat(" ").concat(time.toString());
	                twitterSender.toTweet(messageTemp);
	                String messageHumidity = "#".concat(plantMeta.getPlantName())
	                        .concat(" says: ").concat(plantCheck.humidityStatus())
	                        .concat(" @").concat(plantMeta.getOwnerTwitter());
	                twitterSender.toTweet(messageHumidity);
            	}
            	catch(Exception ex){
            		System.err.println("Maybe that plant doesn' exist");
            		ex.printStackTrace();
            	}
            }
        });

        timer.start();
        
        command_terminal();
        
    }


    private void command_terminal(){
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
        
    	
        while(true){
	        try {
        		command = br.readLine();
	        		
		        switch(command){
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
