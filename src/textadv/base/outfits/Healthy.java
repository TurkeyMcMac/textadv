package textadv.base.outfits;

import textadv.base.directions.RelDir;
import textadv.base.factions.Faction;
import textadv.base.world.Being;
import textadv.base.damage.*;

public interface Healthy {
	
	public default void kill() {
		setAlive(false);
		((Being)this).getController().suppress();
	}
	
	public int getHealth();
	
	public default int changeHealth(int damage) {
		int nowHealth = getHealth() + damage;
		setHealth(nowHealth);
		return nowHealth;
	}
	
	public void setHealth(int health); 
	
	public int getMaxHealth();
	
	public int getShield(RelDir direction);
	
	public Integer getDodge();
	
	public Faction getFaction();
	
	public void setAlive(boolean alive);
	
	public boolean isAlive();
	
	public int resist(DamageType d);
	
	public default int takeDamage(DamageEffect source) {
		int nowHealth = getHealth() - source.apply(this);
		setHealth(nowHealth);
		if (getHealth() < 0) {
			kill();
			setHealth(0);
		}
		return nowHealth;
	}
	
}
