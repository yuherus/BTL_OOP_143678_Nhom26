package application.models;

public class Collection {
	private String collection;
	private double floorPrice;
	private double volumn;
	private double volumnChange;
	
	public Collection(String collection, double floorPrice, double volumn, double volumnChange) {
		super();
		this.collection = collection;
		this.floorPrice = floorPrice;
		this.volumn = volumn;
		this.volumnChange = volumnChange;
	}
	
	public Collection() {
		
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public double getFloorPrice() {
		return floorPrice;
	}

	public void setFloorPrice(double floorPrice) {
		this.floorPrice = floorPrice;
	}

	public double getVolumn() {
		return volumn;
	}

	public void setVolumn(double volumn) {
		this.volumn = volumn;
	}

	public double getVolumnChange() {
		return volumnChange;
	}

	public void setVolumnChange(double volumnChange) {
		this.volumnChange = volumnChange;
	}
	
}
