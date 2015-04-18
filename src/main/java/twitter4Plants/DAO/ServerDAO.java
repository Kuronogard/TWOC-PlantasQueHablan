package twitter4Plants.DAO;

import twitter4Plants.Plants.PlantMeta;
import twitter4Plants.Plants.PlantType;

/**
 * The Interface ServerDAO.
 */
public interface ServerDAO {

	/**
	 * Gets the plant type.
	 *
	 * @param idPlantType
	 *            the id plant type
	 * @return the plant type
	 */
	public PlantType getPlantType(int idPlantType);

	/**
	 * Gets the plant meta.
	 *
	 * @param idPlantMeta
	 *            the id plant meta
	 * @return the plant meta
	 */
	public PlantMeta getPlantMeta(int idPlantMeta);

}
