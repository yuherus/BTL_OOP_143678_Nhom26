package application.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Collection {
	
	 	@JsonProperty("id")
	    private String id;

	    @JsonProperty("volumeNative")
	    private double volumeNative;

	    @JsonProperty("volumeNativeUnit")
	    private String volumeNativeUnit;

	    @JsonProperty("floorPrice")
	    private double floorPrice;

	    @JsonProperty("floorPriceUnit")
	    private String floorPriceUnit;

	    @JsonProperty("VolumeChange")
	    private double volumeChange;

	    @JsonProperty("blockchain")
	    private String blockchain;

	    @JsonProperty("name")
	    private String name;

	    @JsonProperty("description")
	    private String description;

	    @JsonProperty("imageUrl")
	    private String imageUrl;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getBlockchain() {
		return blockchain;
	}
	public void setBlockchain(String blockchain) {
		this.blockchain = blockchain;
	}
	public double getFloorPrice() {
		return floorPrice;
	}
	public void setFloorPrice(double floorPrice) {
		this.floorPrice = floorPrice;
	}
	public String getFloorPriceUnit() {
		return floorPriceUnit;
	}
	public void setFloorPriceUnit(String floorPriceUnit) {
		this.floorPriceUnit = floorPriceUnit;
	}
	public double getVolumeNative() {
		return volumeNative;
	}
	public void setVolumeNative(double volumeNative) {
		this.volumeNative = volumeNative;
	}
	public String getVolumeNativeUnit() {
		return volumeNativeUnit;
	}
	public void setVolumeNativeUnit(String volumeNativeUnit) {
		this.volumeNativeUnit = volumeNativeUnit;
	}
	public double getVolumeChange() {
		return volumeChange;
	}
	public void setVolumeChange(double volumeChange) {
		this.volumeChange = volumeChange;
	}
	@Override
	public String toString() {
		return "Collection [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", blockchain=" + blockchain + ", floorPrice=" + floorPrice + ", floorPriceUnit=" + floorPriceUnit
				+ ", volumeNative=" + volumeNative + ", volumeNativeUnit=" + volumeNativeUnit + ", volumeChange="
				+ volumeChange + "]";
	}
	
	@JsonCreator
	public Collection(@JsonProperty("id") String id,
            @JsonProperty("volumeNative") double volumeNative,
            @JsonProperty("volumeNativeUnit") String volumeNativeUnit,
            @JsonProperty("floorPrice") double floorPrice,
            @JsonProperty("floorPriceUnit") String floorPriceUnit,
            @JsonProperty("VolumeChange") double volumeChange,
            @JsonProperty("blockchain") String blockchain,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("imageUrl") String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.blockchain = blockchain;
		this.floorPrice = floorPrice;
		this.floorPriceUnit = floorPriceUnit;
		this.volumeNative = volumeNative;
		this.volumeNativeUnit = volumeNativeUnit;
		this.volumeChange = volumeChange;
	}
	public Collection(String name, double floorPrice, double volumeNative, double volumeChange) {
		super();
		this.volumeNative = volumeNative;
		this.floorPrice = floorPrice;
		this.volumeChange = volumeChange;
		this.name = name;
	}
	
	
}
