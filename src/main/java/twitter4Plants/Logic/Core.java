package twitter4Plants.Logic;

import twitter4Plants.DAO.DummySensorDAO;
import twitter4Plants.DAO.DummyServerDAO;
import twitter4Plants.DAO.SensorDAO;
import twitter4Plants.DAO.ServerDAO;
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

    private int plantUpdateMins;

    Core(int plantUpdateMins, String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {

        this.plantUpdateMins = plantUpdateMins;

        this.twitterSender = Sender.getInstance(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.sensordao = new DummySensorDAO();
        this.serverdao = new DummyServerDAO();
    }

    public void run() {

        //TODO: configurar y lanzar el timer

        //TODO: mantener la lista de plantas a actualizar
        // - La lee de serverdao

        // La operacion del timer será:
        // - leer la información de sensordao y serverdao
        // - comprobar los parámetros
        // - postear en twitter los resultados
        twitterSender.connect();
        Timer timer = new Timer(plantUpdateMins, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Timestamp time = new Timestamp(System.currentTimeMillis());
                PlantStatus plantStatus = sensordao.getPlantStatus(0);
                PlantMeta plantMeta = serverdao.getPlantMeta(0);
                PlantType plantType = serverdao.getPlantType(plantMeta.getTypeId());
                PlantCheck plantCheck = new PlantCheck(plantStatus, plantType);
                String messageTemp = "#".concat(plantMeta.getPlantName())
                        .concat(" says: ").concat(plantCheck.temperatureStatus())
                        .concat(" @").concat(plantMeta.getOwnerTwitter()).concat(" ").concat(time.toString());
                twitterSender.toTweet(messageTemp);
                String messageHumidity = "#".concat(plantMeta.getPlantName())
                        .concat(" says: ").concat(plantCheck.humidityStatus())
                        .concat(" @").concat(plantMeta.getOwnerTwitter());
            }
        });

        timer.start();
        
        command_terminal();
        
    }

    private void command_terminal(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        String command = new String();
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
	        		serverdao.savePlantMeta(meta);
	        		break;
	        	case "addPlantType":
	        		System.out.print("plant ID: ");
	        		plantID = Integer.parseInt(br.readLine());
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
	        		type = new PlantType(temperatureMax, temperatureMin, temperatureHappy,
	        				humidityMax, humidityMin, humidityHappy,
	        				lightMax, lightMin, lightHappy);
	        		serverdao.savePlantType(type);
	        		break;
	        	default:
	        		System.out.println(command.concat(" Not an existent command."));
	        }
				
			} catch (IOException e1) {
				System.err.println("Console is not working.");
				e1.printStackTrace();
			} catch(NumberFormatException e){
				System.err.println("A param is not correct.");
				e.printStackTrace();
			}
    
        }
    }
}
