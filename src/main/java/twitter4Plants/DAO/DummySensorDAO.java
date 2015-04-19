package twitter4Plants.DAO;

import twitter4Plants.Plants.PlantStatus;

/**
 * The Class DummySensorDAO.
 */
public class DummySensorDAO implements SensorDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see twitter4Plants.DAO.SensorDAO#getPlantStatus(int)
	 */
	@Override
	public PlantStatus getPlantStatus(int idPlantStatus) {

		int id = 0;
		double temperature = 20;
		double humidity = 68;
		double light = 0;
		
		PlantStatus plantStatus = new PlantStatus(id, temperature, humidity,
				light);
		return plantStatus;
	}

}
