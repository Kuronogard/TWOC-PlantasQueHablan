package twitter4Plants.DAO;

import twitter4Plants.Plants.PlantMeta;
import twitter4Plants.Plants.PlantType;

// TODO: Auto-generated Javadoc
/**
 * The Class DummyServerDAO.
 */
public class DummyServerDAO implements ServerDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see twitter4Plants.DAO.ServerDAO#getPlantType(int)
	 */
	@Override
	public PlantType getPlantType(int idPlantType) {

		double temperatureMax = 0;
		double temperatureMin = 0;
		double temperatureHappy = 0;
		double humidityMax = 0;
		double humidityMin = 0;
		double humidityHappy = 0;
		double lightMax = 0;
		double lightMin = 0;
		double lightHappy = 0;
		PlantType plantType = new PlantType(temperatureMax, temperatureMin,
				temperatureHappy, humidityMax, humidityMin, humidityHappy,
				lightMax, lightMin, lightHappy);
		return plantType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see twitter4Plants.DAO.ServerDAO#getPlantMeta(int)
	 */
	@Override
	public PlantMeta getPlantMeta(int idPlantMeta) {

		int plantId = 0;
		int ownerId = 0;
		String plantName = "";
		String plantDescription = "";
		String ownerTwitter = "";
		PlantMeta plantMeta = new PlantMeta(plantId, ownerId, plantName,
				plantDescription, ownerTwitter);
		return plantMeta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * twitter4Plants.DAO.ServerDAO#savePlantType(twitter4Plants.Plants.PlantType
	 * )
	 */
	@Override
	public void savePlantType(PlantType plantType) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * twitter4Plants.DAO.ServerDAO#savePlantMeta(twitter4Plants.Plants.PlantMeta
	 * )
	 */
	@Override
	public void savePlantMeta(PlantMeta plantMeta) {

	}

}
