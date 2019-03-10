package models;

public class MapType {

	private String saveName;
	private String mapLocation;

	public MapType(String saveName, String mapLocation) {
		setSaveName(saveName);
		setMapLocation(mapLocation);
	}

	public String getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	
	
}
