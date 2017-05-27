package textadv.base.population;

import textadv.base.world.*;

import java.util.Map;

import textadv.base.outfits.*;
import textadv.base.resources.Resources;

public class Block extends Pile implements Item, Solid {
	
	@SuppressWarnings("unchecked")
	public Block(Tile tile) {
		super("Block", "A heavy block of material.", (char) ((Map<String, Object>)Resources.get("plant", "things")).get("icon"), tile);
	}
	
	@Override
	public int getWeight() {
		return 500;
	}

}