package twitter4Plants.DAO;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import twitter4Plants.Plants.PlantMeta;
import twitter4Plants.Plants.PlantType;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * The Class ServerDAOImpl.
 */
public class ServerDAOImpl implements ServerDAO {

	String clientUri = "mongodb://plantasquehablan:pl4nt4s1@ds037581.mongolab.com:37581/plantasquehablan2";
	String dbName = "plantasquehablan2";
	
	@Override
	public List<PlantMeta> getAllMetas() {
		ArrayList<PlantMeta> list = new ArrayList<PlantMeta>();
		
		MongoClientURI connectionString = new MongoClientURI(clientUri);
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase(dbName);
		
		MongoCollection<Document> collection = database
				.getCollection("PlantMeta");

		Document plantTypeJson;
		Iterator<Document> itr = collection.find().iterator();
		
		while(itr.hasNext()){
			
			plantTypeJson = itr.next();
			
			int plantId = plantTypeJson.getInteger("idPlantMeta", 0);
			int typeId = plantTypeJson.getInteger("typeId", 0);
			String plantName = plantTypeJson.getString("plantName");
			String ownerTwitter = plantTypeJson.getString("ownerTwitter");
			PlantMeta plantMeta = new PlantMeta(plantId, typeId, plantName,
					ownerTwitter);
			
			list.add(plantMeta);
		}
		
		mongoClient.close();
		
		return list;
	}	

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see twitter4Plants.DAO.ServerDAO#getPlantType(int)
	 */
	@Override
	public PlantType getPlantType(int idPlantType) {

		MongoClientURI connectionString = new MongoClientURI(clientUri);
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase(dbName);

		MongoCollection<Document> collection = database
				.getCollection("PlantType");

		Document plantTypeJson = collection
				.find(eq("idPlantType", idPlantType)).first();

		double temperatureMax = plantTypeJson.getDouble("temperatureMax");
		double temperatureMin = plantTypeJson.getDouble("temperatureMin");
		double temperatureHappy = plantTypeJson.getDouble("temperatureHappy");
		double humidityMax = plantTypeJson.getDouble("humidityMax");
		double humidityMin = plantTypeJson.getDouble("humidityMin");
		double humidityHappy = plantTypeJson.getDouble("humidityHappy");
		double lightMax = plantTypeJson.getDouble("lightMax");
		double lightMin = plantTypeJson.getDouble("lightMin");
		double lightHappy = plantTypeJson.getDouble("lightHappy");

		PlantType plantType = new PlantType(idPlantType, temperatureMax,
				temperatureMin, temperatureHappy, humidityMax, humidityMin,
				humidityHappy, lightMax, lightMin, lightHappy);

		mongoClient.close();

		return plantType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see twitter4Plants.DAO.ServerDAO#getPlantMeta(int)
	 */
	@Override
	public PlantMeta getPlantMeta(int idPlantMeta) {
		MongoClientURI connectionString = new MongoClientURI(clientUri);
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase(dbName);

		MongoCollection<Document> collection = database
				.getCollection("PlantMeta");

		Document plantTypeJson = collection
				.find(eq("idPlantMeta", idPlantMeta)).first();

		int plantId = plantTypeJson.getInteger("idPlantMeta", 0);
		int typeId = plantTypeJson.getInteger("typeId", 0);
		String plantName = plantTypeJson.getString("plantName");
		String ownerTwitter = plantTypeJson.getString("ownerTwitter");
		PlantMeta plantMeta = new PlantMeta(plantId, typeId, plantName,
				ownerTwitter);

		mongoClient.close();

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
		MongoClientURI connectionString = new MongoClientURI(clientUri);
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase(dbName);

		MongoCollection<Document> collection = database
				.getCollection("PlantType");

		Document document = new Document();
		document.append("idPlantType", plantType.getId());

		document.append("temperatureMax", plantType.getTemperatureMax());
		document.append("temperatureMin", plantType.getTemperatureMin());
		document.append("temperatureHappy", plantType.getTemperatureHappy());

		document.append("humidityMax", plantType.getHumidityMax());
		document.append("humidityMin", plantType.getHumidityMin());
		document.append("humidityHappy", plantType.getHumidityHappy());

		document.append("lightMax", plantType.getLightMax());
		document.append("lightMin", plantType.getLightMin());
		document.append("lightHappy", plantType.getLightHappy());

		collection.insertOne(document);

		mongoClient.close();
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

		MongoClientURI connectionString = new MongoClientURI(clientUri);
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase(dbName);

		MongoCollection<Document> collection = database
				.getCollection("PlantMeta");

		Document document = new Document();
		document.append("idPlantMeta", plantMeta.getPlantId());

		document.append("typeId", plantMeta.getTypeId());
		document.append("plantName", plantMeta.getPlantName());
		document.append("ownerTwitter", plantMeta.getOwnerTwitter());
		collection.insertOne(document);

		mongoClient.close();

	}

}
