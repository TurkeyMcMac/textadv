package textadv.base.world;

import java.util.Map;

import textadv.base.control.Controller;
import textadv.base.damage.*;
import textadv.base.directions.CarDir;
import textadv.base.directions.RelDir;
import textadv.base.factions.Faction;
import textadv.base.outfits.Healthy;
import textadv.base.outfits.Hostile;

public class Monster extends Being implements Healthy, Hostile {
	
	private static final long serialVersionUID = -3790204973600181028L;
	
	private Faction faction;
	private boolean alive = true;
	private Map<DamageType, Integer> resistance;
	protected Map<RelDir, Integer> shields;
	private int nowHealth;
	private int maxHealth;
	private int dodge;
	
	public Monster(String name,
				   String info,
				   char icon,
				   Faction faction,
				   int maxHealth,
				   int dodge,
				   CarDir facing,
				   Controller<?> controller,
				   Map<DamageType, Integer> resistance,
				   Map<RelDir, Integer> shields)
	{
		super(name, info, icon, facing, controller);
		this.faction = faction;
		this.nowHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.dodge = dodge;
		this.resistance = resistance;
		this.shields = shields;
	}
	
	@Override
	public DamageEffect getAttack(String name) {
		return getAttacks().get(name);
	}

	@Override
	public int getHealth() {
		return nowHealth;
	}

	@Override
	public void setHealth(int health) {
		nowHealth = Math.min(health, maxHealth);
	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public int getShield(RelDir direction) {
		return shields.get(direction);
	}

	@Override
	public Integer getDodge() {
		return dodge;
	}
	
	public Faction getFaction() {
		return faction;
	}
	
	@Override
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public int resist(DamageType d) {
		if (resistance.containsKey(d)) {
			return resistance.get(d);
		} else {
			return 1;
		}
	}
	
	@Override
	public String toString() {
		StringBuffer stringified = new StringBuffer(describe());
		stringified.append("\n" + (alive ? "Alive" : "Corpse"));
		stringified.append("\nFaction: " + faction.getName());
		stringified.append("\nHealth: " + nowHealth + "/" + maxHealth);
		stringified.append("\nDodge: " + dodge);
		stringified.append("\nResistance: ");
		for (DamageType d : resistance.keySet()) {
			stringified.append("\n\t" + d.toString().toLowerCase() + ": " + resistance.get(d));
		}
		stringified.append("\nShields: ");
		for (RelDir d : shields.keySet()) {
			stringified.append("\n\t" + d.toString().toLowerCase() + ": " + shields.get(d));
		}
		return stringified.toString();
	}

	@Override
	public Map<String, DamageEffect> getAttacks() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
