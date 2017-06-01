package textadv.base.population;

import textadv.base.world.Pile;
import textadv.base.outfits.Item;

public class Poop extends Pile implements Item {

	public Poop() {
		super("Poop", "Smelly", ';');
	}

	@Override
	public int getWeight() {
		return 1;
	}

}
