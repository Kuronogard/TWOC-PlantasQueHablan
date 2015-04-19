package twitter4Plants.DAO;

import java.util.List;

import twitter4Plants.Plants.PlantMeta;
import twitter4Plants.Plants.PlantType;

// TODO: Auto-generated Javadoc
/**
 * The Interface ServerDAO.
 */
public interface ServerDAO {

	/**
	 * Gets all the registered Plants in a List
	 * 
	 * @return A list containing all registered plants
	 */
	public List<PlantMeta> getAllMetas();
	
	/**
	 * Gets the plant type.
	 *
	 * @param idPlantType
	 *            the id plant type
	 * @return the plant type
	 */
	public PlantType getPlantType(int idPlantType);

	/**
	 * Save plant type.
	 *
	 * @param plantType the plant type
	 */
	public void savePlantType(PlantType plantType );
	/**
	 * Gets the plant meta.
	 *
	 * @param idPlantMeta
	 *            the id plant meta
	 * @return the plant meta
	 */
	public PlantMeta getPlantMeta(int idPlantMeta);

	/**
	 * Save plant meta.
	 *
	 * @param plantMeta the plant meta
	 */
	public void savePlantMeta(PlantMeta plantMeta);
	
}
