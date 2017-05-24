package textadv.base.outfits;

import textadv.base.damage.DamageEffect;
import textadv.base.factions.Faction;
import textadv.base.world.Tile;;

public interface Hostile {
	
	public Faction getFaction();
	
	public DamageEffect getAttack(String name);
	
	public default int attack(Healthy subject, String atkUsed) {
		return subject.takeDamage(getAttack(atkUsed));
	}
	
}
