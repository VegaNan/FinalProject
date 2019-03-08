package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
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
