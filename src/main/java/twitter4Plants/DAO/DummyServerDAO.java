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

		double temperatureMax = 25;
		double temperatureMin = 15;
		double temperatureHappy = 20;
		double humidityMax = 80;
		double humidityMin = 50;
		double humidityHappy = 68;
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
		int typeId = 0;
		String plantName = "planticadev";
		String ownerTwitter = "alvarocaste";
		PlantMeta plantMeta = new PlantMeta(plantId, typeId, plantName,
				ownerTwitter);
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
