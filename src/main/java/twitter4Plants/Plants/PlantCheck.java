package twitter4Plants.Plants;

public class PlantCheck {

	private PlantStatus status;
	private PlantType type;
	
	
	public PlantCheck(PlantStatus status, PlantType type){
		this.status = status;
		this.type = type;
	}
	
	public boolean isTempCorrect(){
		return (status.getTemperature() < type.getTemperatureMax()) && (status.getTemperature() < type.getTemperatureMin());
	}
	
	public boolean isHumidityCorrect(){
		return (status.getHumidity() < type.getHumidityMax()) && (status.getHumidity() < type.getHumidityMin());
	}
	
	public boolean isLightCorrect(){
		return (status.getLight() < type.getLightMax()) && (status.getLight() < type.getLightMin());
	}
	
	public String temperatureStatus(){
		String plantstatus;
		
		if( status.getTemperature() < type.getTemperatureMin())
			plantstatus = "ggggrgrgrgrgrgr... i'm freeeeeeezing";
		else if( status.getTemperature() > type.getTemperatureMax())
			plantstatus = "Fuck, it's damn hot here!";
		else if( status.getTemperature() == type.getTemperatureHappy())
			plantstatus = "Niiiiiiiiice";
		else
			plantstatus = "laurelindórenan lindelorendor malinornélion ornemalin";
		
		return plantstatus;
	}
	
	public String humidityStatus(){
		String plantstatus;
		
		if( status.getHumidity() < type.getHumidityMin())
			plantstatus = "Look! i'm transforming into a FUCKING CACTUS!";
		else if( status.getHumidity() > type.getHumidityMax())
			plantstatus = "wet, wet, wet...";
		else if( status.getHumidity() == type.getHumidityHappy())
			plantstatus = "^^ i couldn't be more happy";
		else
			plantstatus = "Taurelilómëa-tumbalemorna Tumbaletaurëa Lómëanor";
		
		return plantstatus;
	}
	
	public String lightStatus(){
		String plantstatus;
		
		if( status.getLight() < type.getLightMin())
			plantstatus = "What a beautifull night... oh wait!";
		else if( status.getLight() > type.getLightMax())
			plantstatus = "My plant-eyes are burned!, too much light...";
		else if( status.getLight() == type.getLightHappy())
			plantstatus = "yumi, yumi, what a delightfull photosynthesis";
		else
			plantstatus = "a-lalla-lalla-rumba-kamanda-lindor-burúme";
		
		return plantstatus;
	}
	
}
