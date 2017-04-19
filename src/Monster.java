
import java.util.HashMap;
import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class Monster extends Being implements Solid, Graphic {
	
	char icon;
	boolean alive;
	Faction faction;
	int maxHealth;
	int nowHealth;
	HashMap<DamageType, Integer> resistance;
	int dodge;
	HashMap<RelDir, Integer> shields;
	
	public Monster(
		String name,
		String info,
		CarDir facing,
		ArrayList<Consumer<Being>> sequence,
		char icon,
		Faction faction,
		
		int health,
		HashMap<DamageType, Integer> resistance,
		int dodge,
		
		int shieldF,
		int shieldR,
		int shieldB,
		int shieldL,
		
		Tile tile) 
	{
		super(name, info, facing, sequence, tile);
		this.icon = icon;
		this.alive = true;
		this.faction = faction;
		this.facing = facing;
		this.maxHealth = health;
		this.nowHealth = health;
		this.resistance = resistance;
		this.dodge = dodge;
		this.shields = new HashMap<RelDir, Integer>();
		shields.put(RelDir.FORWARD, shieldF);
		shields.put(RelDir.RIGHT, shieldR);
		shields.put(RelDir.BACKWARD, shieldB);
		shields.put(RelDir.LEFT, shieldL);
	}
	
	public char getIcon() {
		return icon;
	}
	
	@Override
	public void update() {
		if (isAlive()) {
			control();
		}
	}
	
	public void kill() {
		alive = false;
	}
	
	public boolean isAlive() {
		alive = nowHealth > 0;
		return alive;
	}
	
	public Tile look(int x, int y) {
		return tile.grid.spot(x, y);
	}
	
	public Tile look(CarDir d) {
		switch (facing) {
			case NORTH:
				return look(0, -1);
			case EAST:
				return look(+1, 0);
			case SOUTH:
				return look(0, +1);
			case WEST:
				return look(-1, 0);
			default:
				return tile;
		}
	}
	
	public int resist(DamageType d) {
		if (resistance.containsKey(d)) {
			return resistance.get(d);
		} else {
			return 1;
		}
	}
	
}
