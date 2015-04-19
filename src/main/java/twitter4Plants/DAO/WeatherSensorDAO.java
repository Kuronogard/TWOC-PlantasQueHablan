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
        owmClient.setAPPID("845919ef3453dfad28614165da748b6f");
        try {
            PlantStatus plantStatus = null;
            WeatherStatusResponse weatherStatusResponse = owmClient.currentWeatherAtCity("Madrid","es");
            double temp = Double.parseDouble(weatherStatusResponse.getWeatherStatus().get(0));
            //double humidity = weatherStatusResponse.getWeatherStatus().get(0).getHumidity();
            //double light = weatherStatusResponse.getWeatherStatus().get(0).getClouds().getAll();
            //plantStatus = new PlantStatus(idPlantStatus,temp,humidity,light);
            return plantStatus;
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

}
