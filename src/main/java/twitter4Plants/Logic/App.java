package twitter4Plants.Logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.*;

/*
 * .setOAuthConsumerKey("y1zM2z2cnqfo80qLuMcdAGP4b")
 * .setOAuthConsumerSecret("7GYuaitHZ5d0MmoSk2nTHf9NICOJv6hGxBRF1Bqb3v8CLpxIS9")
 * .setOAuthAccessToken("3179195939-VwKPvlLgnkMFOpYqp8Mph9e9q4geuCZC3t6GcjA")
 * .setOAuthAccessTokenSecret("pNDDi7kdb9BB14RrJGRz9ide9v3uVEqECtlEhJ6T5mt4g");
 */

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
    	
    	Core core;
    	
    	FileReader file = null;
    	BufferedReader reader = null;
    	
    	Options argOptions = new Options();
    	CommandLineParser argParser = new BasicParser();
    	CommandLine cmd = null;
 
    	String authConsumerKey = null;
        String authConsumerSecret = null;
        String authAccessToken = null;
        String authAccessTokenSecret = null;
        
    	int plantUpdateMins = 20;
    	String configurationFile = "Test.tcnf";
    	
    	argOptions.addOption("h", false, "Get this help");
    	argOptions.addOption("t", true, "Plant update time");
    	argOptions.addOption("f", true, "Twitter credentials file");
    	
    	try {
			cmd = argParser.parse(argOptions, args);
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(-1);
		}
    	
    	if(cmd.hasOption("h")){
    		System.out.println("t <update_mins_intervale> -f <file.tcnf>");
    		System.exit(0);
    	}
    	
    	if(cmd.hasOption("f")){
    		configurationFile = cmd.getOptionValue("f");
    	}
    	
    	if(cmd.hasOption("t")){
    		try{
    			plantUpdateMins = Integer.parseInt(cmd.getOptionValue("t"));
    		}
    		catch(NumberFormatException e){
    			e.printStackTrace();
    			System.exit(-1);
    		}
    	}
   		
    	try {
			file = new FileReader(configurationFile);
			reader = new BufferedReader(file);
	    	authConsumerKey = reader.readLine();
	        authConsumerSecret = reader.readLine();
	        authAccessToken = reader.readLine();
	        authAccessTokenSecret = reader.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch(IOException e){
			e.printStackTrace();
			System.exit(-1);;
		} finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
    	
    	core = new Core(plantUpdateMins, authConsumerKey, authConsumerSecret, authAccessToken, authAccessTokenSecret); 
    	
    	core.run();
    }
}
