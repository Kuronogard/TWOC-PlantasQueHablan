package twitter4Plants.Plants;

public class PlantMeta {

	private int plantId;
	private int typeId;
	private String plantName;
	private String ownerTwitter;

	
	public PlantMeta(int plantId, int typeId, String plantName, String ownerTwitter){
		this.plantId = plantId;
		this.typeId = typeId;
		this.plantName = plantName;
		this.ownerTwitter = ownerTwitter;
	}

	public int getPlantId() {
		return plantId;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getPlantName() {
		return plantName;
	}

	public String getOwnerTwitter() {
		return ownerTwitter;
	}
}
