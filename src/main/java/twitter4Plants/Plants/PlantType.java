package twitter4Plants.Plants;

public class PlantType {

	private int id;
	
	private double temperatureMax;
	private double temperatureMin;
	private double temperatureHappy;
	
	private double humidityMax;
	private double humidityMin;
	private double humidityHappy;
	
	private double lightMax;
	private double lightMin;
	private double lightHappy;

	
	public PlantType(double temperatureMax, double temperatureMin, double temperatureHappy,
			double humidityMax, double humidityMin, double humidityHappy,
			double lightMax, double lightMin, double lightHappy){
		this.temperatureMax = temperatureMax;
		this.temperatureMin = temperatureMin;
		this.temperatureHappy = temperatureHappy;
		this.humidityMax = humidityMax;
		this.humidityMin = humidityMin;
		this.humidityHappy = humidityHappy;
		this.lightMax = lightMax;
		this.lightMin = lightMin;
		this.lightHappy = lightHappy;
	}
	
	public int getId() {
		return id;
	}
	
	public double getTemperatureMax() {
		return temperatureMax;
	}
	
	public double getTemperatureMin() {
		return temperatureMin;
	}
	
	public double getTemperatureHappy() {
		return temperatureHappy;
	}
	
	public double getHumidityMax() {
		return humidityMax;
	}
	
	public double getHumidityMin() {
		return humidityMin;
	}
	
	public double getHumidityHappy() {
		return humidityHappy;
	}
	
	public double getLightMax() {
		return lightMax;
	}
	
	public double getLightMin() {
		return lightMin;
	}
	
	public double getLightHappy() {
		return lightHappy;
	}
}
