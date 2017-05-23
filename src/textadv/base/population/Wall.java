package textadv.base.population;

import textadv.base.world.Ground;
import textadv.base.world.Tile;
import textadv.base.outfits.Solid;

public class Wall extends Ground implements Solid {
	
	public Wall(String name, String info, char icon, Tile tile) {
		super(name, info, icon, tile);
	}
	
}
