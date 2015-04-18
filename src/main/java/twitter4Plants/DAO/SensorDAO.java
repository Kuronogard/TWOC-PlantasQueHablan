package twitter4Plants.DAO;

import twitter4Plants.Plants.PlantStatus;

/**
 * The Interface SensorDAO.
 */
public interface SensorDAO {

	/**
	 * Gets the plant status.
	 *
	 * @param idPlantStatus
	 *            the id plant status
	 * @return the plant status
	 */
	public PlantStatus getPlantStatus(int idPlantStatus);

}
