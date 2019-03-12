package interfaces;

import models.Armor;
import models.Player;
import models.Weapon;

public interface Equippable {
	public String getDescription();
	public void equip(Player player1);
}
