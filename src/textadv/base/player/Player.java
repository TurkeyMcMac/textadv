package textadv.base.player;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import textadv.base.control.*;
import textadv.base.damage.*;
import textadv.base.directions.*;
import textadv.base.factions.Faction;
import textadv.base.outfits.*;
import textadv.base.resources.Resources;
import textadv.base.world.*;

public final class Player extends Monster implements WeaponWielder, ArmorWearer, Solid {
	
	private List<Item> inventory = new ArrayList<Item>();
	private int nowWeight = 0;
	private int maxWeight = 10;
	private List<Tool<?>> wielded = new ArrayList<>();
	private Map<Armor, RelDir> armors = new HashMap<>();
	private int nowWield = 0;
	private int maxWield = 2;
	private Talker listenedTo;
	
	@SuppressWarnings("unchecked")
	private static final Map<String, Object> RESOURCES = (Map<String, Object>)Resources.get("player", "things");

	public static final String NAME = (String)RESOURCES.get("name");
	
	public static final String INFO = (String)RESOURCES.get("info");
	
	public static final Character ICON = (Character)RESOURCES.get("icon");
	
	public static final Faction FACTION = Faction.get("Player");
	
	public static final int MAX_HEALTH = 40;
	
	public static final int DODGE = 10;
	
	public static final PlayerController CONTROLLER = new PlayerController();
	
	public static final Map<DamageType, Integer> RESISTANCE = new HashMap<>();
	
	public static final Map<String, DamageEffect> ATTACKS = new HashMap<>();
	
	@SuppressWarnings("serial")
	public static final Map<RelDir, Integer> SHIELDS = new HashMap<RelDir, Integer>() {{
		put(RelDir.FORWARD, 2);
		put(RelDir.RIGHT, 2);
		put(RelDir.BACKWARD, 2);
		put(RelDir.LEFT, 2);
	}};
	
	public Player(CarDir facing, Tile tile) {
		super(NAME, 
			  INFO,
			  ICON,
			  FACTION,
			  MAX_HEALTH,
			  DODGE,
			  facing, 
			  CONTROLLER,
			  ATTACKS,
			  RESISTANCE,
			  SHIELDS,
			  tile);
		
	}
	
	@Override
	protected void doUpdate() {
		controller.control();
		tile.getGrid().setPlayerTile(tile);
	}
	
	@Override
	public PlayerController getController() {
		return (PlayerController)controller;
	}
	
	@Override
	public boolean pickUp(Item item) {
		if (nowWeight + item.getWeight() <= maxWeight && ((Thing)item).getTile() == tile) {
			((Pile)item).pickUp();
			((Thing)item).setTile(null);
			inventory.add(item);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean dropOff(Item item) {
		if (inventory.remove(item)) {
			if (item instanceof Tool<?>) {
				unwield((Tool<?>) item);
			}
			if (item instanceof Armor) {
				unwear((Armor) item);
			}
			((Pile)item).drop(tile);
			nowWeight -= item.getWeight();
			return true;
		}
		return false;
	}

	@Override
	public void dropAll() {
		for (Item i : inventory) {
			dropOff(i);
		}
	}

	@Override
	public DamageEffect getAttack(String name) {
		return attacks.get(name);
	}

	@Override
	public void addAttack(DamageEffect attack) {
		attacks.put(attack.getName(), attack);
	}

	@Override
	public void remAttack(DamageEffect attack) {
		attacks.remove(attack.getName());
	}

	@Override
	public boolean wield(Tool<?> tool) {
		if (inventory.contains(tool) && nowWield < maxWield) {
			wielded.add(tool);
			if (tool instanceof Weapon) {
				((Weapon) tool).wield(this);
			}
			++nowWield;
			return true;
		}
		return false;
	}

	@Override
	public boolean unwield(Tool<?> tool) {
		if (wielded.remove(tool)) {
			if (tool instanceof Weapon) {
				((Weapon) tool).unwield();
			}
			--nowWield;
			return true;
		}
		return false;
	}

	@Override
	public boolean wear(Armor armor, RelDir direction) {
		if (inventory.contains(armor)) {
			armor.wear(this);
			armors.put(armor, direction);
			shields.replace(direction, shields.get(direction) + armor.getArmor());
			return true;
		}
		return false;
	}

	@Override
	public boolean unwear(Armor armor) {
		RelDir armorSide = armors.get(armor);
		if (armors.remove(armor) != null) {
			armor.unwear();
			shields.replace(armorSide, shields.get(armorSide) - armor.getArmor());
			return true;
		}
		return false;
	}
	
	public String listenTo(Talker talker, String talkName) {
		if (listenedTo != null) {
			listenedTo.endTalk();
		}
		listenedTo = talker;
		return talker.startTalk(talkName);
	}
	
	public String respond(int index) {
		if (listenedTo != null) {
			return listenedTo.talk(index);
		}
		return null;
	}
	
	public void stopListening() {
		listenedTo.endTalk();
		listenedTo = null;
	}
	
}
