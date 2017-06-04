package textadv.base.population;

import textadv.base.world.Ground;
import textadv.base.outfits.Solid;

public class Wall extends Ground implements Solid {
	
	private static final long serialVersionUID = -7048221051126379647L;

	public Wall(String name, String info, char icon) {
		super(name, info, icon);
	}
	
}
