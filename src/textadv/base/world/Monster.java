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
	protected Map<String, DamageEffect> attacks;
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
				   Map<String, DamageEffect> attacks,
				   Map<DamageType, Integer> resistance,
				   Map<RelDir, Integer> shields)
	{
		super(name, info, icon, facing, controller);
		this.faction = faction;
		this.nowHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.dodge = dodge;
		this.attacks = attacks;
		this.resistance = resistance;
		this.shields = shields;
	}
	
	@Override
	public DamageEffect getAttack(String name) {
		return attacks.get(name);
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
		String stringified = describe();
		stringified += "\n" + (alive ? "Alive" : "Corpse");
		stringified += "\nFaction: " + faction.getName();
		stringified += "\nHealth: " + nowHealth + "/" + maxHealth;
		stringified += "\nDodge: " + dodge;
		stringified += "\nResistance: ";
		for (DamageType d : resistance.keySet()) {
			stringified += "\n\t" + d.toString().toLowerCase() + ": " + resistance.get(d);
		}
		stringified += "\nShields: ";
		for (RelDir d : shields.keySet()) {
			stringified += "\n\t" + d.toString().toLowerCase() + ": " + shields.get(d);
		}
		return stringified;
	}
	
}
