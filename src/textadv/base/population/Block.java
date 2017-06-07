package textadv.base.population;

import textadv.base.world.*;

import java.util.Map;

import textadv.base.outfits.*;
import textadv.base.resources.Things;

public class Block extends Pile implements Item, Solid {
	
	private static final long serialVersionUID = -1532932248711626371L;

	@SuppressWarnings("unchecked")
	public Block() {
		super("Block", "A heavy block of material.", (char) ((Map<String, Object>)Things.get("plant")).get("icon"));
	}
	
	@Override
	public int getWeight() {
		return 2;
	}

}
