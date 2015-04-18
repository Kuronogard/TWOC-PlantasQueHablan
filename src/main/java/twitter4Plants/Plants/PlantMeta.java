package twitter4Plants.Plants;

public class PlantMeta {

	private int plantId;
	private int ownerId;
	private String plantName;
	private String plantDescription;
	private String ownerTwitter;

	
	public PlantMeta(int plantId, int ownerId, String plantName, String plantDescription, String ownerTwitter){
		this.plantId = plantId;
		this.ownerId = ownerId;
		this.plantName = plantName;
		this.plantDescription = plantDescription;
		this.ownerTwitter = ownerTwitter;
	}

	public int getPlantId() {
		return plantId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public String getPlantName() {
		return plantName;
	}

	public String getPlantDescription() {
		return plantDescription;
	}

	public String getOwnerTwitter() {
		return ownerTwitter;
	}
}
