package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Map implements Serializable{
	protected HashMap< String, Space> spaces;

	public Map(HashMap<String, Space> spaces)
	{
		setSpaces(spaces);
	}
	public HashMap<String, Space> getSpaces() {
		return spaces;
	}

	public void setSpaces(HashMap<String, Space> spaces) {
		this.spaces = spaces;
	}
	
	
}
