package textadv.base.population;

import java.util.HashMap;
import java.util.Map;

import textadv.base.control.ConstantController;
import textadv.base.damage.DamageEffect;
import textadv.base.damage.DamageType;
import textadv.base.directions.CarDir;
import textadv.base.directions.RelDir;
import textadv.base.factions.Faction;
import textadv.base.outfits.*;
import textadv.base.resources.Things;
import textadv.base.talks.EndTalk;
import textadv.base.talks.Talk;
import textadv.base.world.Being;
import textadv.base.world.Monster;
import textadv.base.world.Tile;

public final class Goblin extends Monster implements Talker, Solid, Item {
	
	private static final long serialVersionUID = -468927277833713444L;

	@SuppressWarnings("unchecked")
	private static final Map<String, Object> RESOURCES = (Map<String, Object>)Things.get("goblin-med");
	
	private Talk nowTalk;
	private boolean talking;
	private Talk talk = 
		new Talk("Hello", Talk.nexts(
			"Hi", new EndTalk("", this),
			"Hello", new Talk("Hey", Talk.nexts(
				"Hi", new EndTalk("", this),
				"Hey", new EndTalk("Foo", this)
			))
		));
	
	@SuppressWarnings("serial")
	public Goblin(CarDir facing) {
		super((String)RESOURCES.get("name"),
			  (String)RESOURCES.get("info"),
			  (Character)RESOURCES.get("icon"),
			  Faction.get("Goblins"),
			  5,
			  5,
			  facing,
			  new ConstantController((c) -> {
				  Being b = c.getControlled();
				  Tile goblinTile = b.getTile();
				  Tile playerTile = goblinTile.getGrid().getPlayer().getTile();
				  goblinTile.getGrid().drop(new Poop(), goblinTile.getX(), goblinTile.getY());
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
			  }});
	}

	@Override
	public String talk(int option) {
		nowTalk = nowTalk.pick(option);
		if (nowTalk != null) {
			return nowTalk.toString();
		}
		return "Invalid option.";
	}

	@Override
	public void endTalk() {
		talking = false;
		nowTalk = null;
	}

	@Override
	public String startTalk() {
		talking = true;
		return (nowTalk = talk).toString();
		
	}
	
	@Override
	public boolean isTalking() {
		return talking;
	}

	@Override
	public int getWeight() {
		return 1;
	}
	
}
