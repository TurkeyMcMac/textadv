package textadv.base.population;

import textadv.base.world.Pile;
import textadv.base.outfits.Armor;
import textadv.base.outfits.ArmorWearer;

public final class Hat extends Pile implements Armor {
	
	int weight = 1;
	int armor = 5;
	ArmorWearer user;
	
	public Hat() {
		super("Hat", "Wow, this hat is cool!", '@');
	}
	
	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public ArmorWearer getUser() {
		return user;
	}

	@Override
	public void wear(ArmorWearer user) {
		this.user = user;
	}

	@Override
	public void unwear() {
		user = null;
	}

	@Override
	public int getArmor() {
		return armor;
	}

}
