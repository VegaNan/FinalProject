package models;

public abstract class Item {
	public int value;
	public String name;
	public String[] items = { "Bear Pelt", "Jar of Incense", "Rabbit Skin", "Beer", "Tobacco", "Cloth", "Rug",
	"Coffee Beans" };

	public Item() {
		setName(items[1]);
		setValue(1);
	}

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
