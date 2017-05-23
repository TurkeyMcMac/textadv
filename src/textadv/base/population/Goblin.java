package textadv.base.population;

import java.util.HashMap;
import java.util.Map;

import textadv.base.control.ConstantController;
import textadv.base.damage.DamageEffect;
import textadv.base.damage.DamageType;
import textadv.base.directions.CarDir;
import textadv.base.directions.RelDir;
import textadv.base.factions.Faction;
import textadv.base.outfits.Solid;
import textadv.base.resources.Resources;
import textadv.base.world.Monster;
import textadv.base.world.Pile;
import textadv.base.world.Tile;

public final class Goblin extends Monster implements Solid {
	
	@SuppressWarnings("unchecked")
	private static final Map<String, Object> RESOURCES = (Map<String, Object>)Resources.get("goblin-med", "things");
	
	@SuppressWarnings("serial")
	public Goblin(CarDir facing, Tile tile) {
		super((String)RESOURCES.get("name"),
			  (String)RESOURCES.get("info"),
			  (Character)RESOURCES.get("icon"),
			  Faction.get("Goblins"),
			  20,
			  10,
			  facing,
			  new ConstantController((b, c) -> {
				  Tile goblinTile = b.getTile();
				  Tile playerTile = goblinTile.getGrid().getPlayerTile();
				  goblinTile.getGrid().drop(new Pile("Poop", "Smelly.", '@', null), goblinTile.getX(), goblinTile.getY());
				  int relativeX = playerTile.getX() - goblinTile.getX(),
				  	  relativeY = playerTile.getY() - goblinTile.getY();
				  b.turn(CarDir.directionOf(relativeX, relativeY));
				  b.step();
			  }),
			  new HashMap<String, DamageEffect>(),
			  new HashMap<DamageType, Integer>() {{
				  put(DamageType.ACID,	2);
				  put(DamageType.POISON,2);
			  }},
			  new HashMap<RelDir, Integer>() {{
				  put(RelDir.FORWARD, 3);
				  put(RelDir.RIGHT, 2);
				  put(RelDir.BACKWARD, 1);
				  put(RelDir.LEFT, 2);
			  }},
			  tile);
	}
	
}
