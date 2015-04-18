package twitter4Plants.Plants;

public class PlantStatus {

	private int id;

	private double temperature;
	private double humidity;
	private double light;

	public PlantStatus(int id, double temperature, double humidity, double light) {
		this.id = id;
		this.temperature = temperature;
		this.humidity = humidity;
		this.light = light;
	}

	public int getID() {
		return id;
	}

	public double getTemperature() {
		return temperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public double getLight() {
		return light;
	}

}
