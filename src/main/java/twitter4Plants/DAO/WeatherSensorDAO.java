package twitter4Plants.DAO;

import twitter4Plants.Plants.PlantStatus;
import twitter4Plants.Weather.OwmClient;
import twitter4Plants.Weather.WeatherStatusResponse;

/**
 * The Class SensorDAOImpl.
 */
public class WeatherSensorDAO implements SensorDAO {

    /*
     * (non-Javadoc)
     *
     * @see twitter4Plants.DAO.SensorDAO#getPlantStatus(int)
     */
    @Override
    public PlantStatus getPlantStatus(int idPlantStatus) {
        OwmClient owmClient = new OwmClient();
        try {
            PlantStatus plantStatus = null;
            WeatherStatusResponse weatherStatusResponse = owmClient.currentWeatherAtCity("Madrid","es");
            Float temp = weatherStatusResponse.getWeatherStatus().get(0).getTemp();
            Float humidity = weatherStatusResponse.getWeatherStatus().get(0).getHumidity();
            int light = weatherStatusResponse.getWeatherStatus().get(0).getClouds().getAll();
            plantStatus = new PlantStatus(idPlantStatus,temp,humidity,light);
            return plantStatus;
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

}
