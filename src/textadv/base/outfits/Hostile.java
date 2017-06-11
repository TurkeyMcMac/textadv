package textadv.base.outfits;

import java.util.Map;

import textadv.base.damage.DamageEffect;
import textadv.base.factions.Faction;

public interface Hostile {
	
	public Faction getFaction();
	
	public DamageEffect getAttack(String name);
	
	public default int attack(Healthy subject, String atkUsed) {
		return subject.takeDamage(getAttack(atkUsed));
	}
	
	public Map<String, DamageEffect> getAttacks();
	
}
