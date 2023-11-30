package application.models;

public class Collection {
	private String id;
	private String collection;
	private String description;
	private String imageUrl;
	private String backgroundUrl;
	private String blockchain;
	private int item;
	private double floorPrice;
	private double volume;
	private double volumeChange;
	
	public Collection(String id, String collection, String description, String imageUrl, String backgroundUrl,
			String blockchain, int item, double floorPrice, double volume, double volumeChange) {
		super();
		this.id = id;
		this.collection = collection;
		this.description = description;
		this.imageUrl = imageUrl;
		this.backgroundUrl = backgroundUrl;
		this.blockchain = blockchain;
		this.item = item;
		this.floorPrice = floorPrice;
		this.volume = volume;
		this.volumeChange = volumeChange;
	}

	public Collection(String collection, double floorPrice, double volume, double volumeChange) {
		super();
		this.collection = collection;
		this.floorPrice = floorPrice;
		this.volume = volume;
		this.volumeChange = volumeChange;
	}



	public Collection() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	
	
	@Override
	public String toString() {
		return "Collection [id=" + id + ", collection=" + collection + ", description=" + description + ", imageUrl="
				+ imageUrl + ", backgroundUrl=" + backgroundUrl + ", blockchain=" + blockchain + ", item=" + item
				+ ", floorPrice=" + floorPrice + ", volume=" + volume + ", volumeChange=" + volumeChange + "]";
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public String getBlockchain() {
		return blockchain;
	}

	public void setBlockchain(String blockchain) {
		this.blockchain = blockchain;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public double getFloorPrice() {
		return floorPrice;
	}

	public void setFloorPrice(double floorPrice) {
		this.floorPrice = floorPrice;
	}

	public double getVolume() {
		return volume;
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getVolumeChange() {
		return volumeChange;
	}


	public void setVolumeChange(double volumeChange) {
		this.volumeChange = volumeChange;
	}
	
}
