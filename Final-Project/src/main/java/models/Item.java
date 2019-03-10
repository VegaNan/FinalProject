package models;

import java.io.Serializable;

public abstract class Item implements Serializable{
	public int value;
	public String name;

	public Item(String name, int value) {
		setName(name);
		setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item \t[name=").append(name).append(", \t\t\t\t\t\t\t\t\t\t\t\t\tvalue=").append(value)
				.append("]");
		return builder.toString();
	}

}
