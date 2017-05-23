package textadv.base.outfits;

import textadv.base.damage.DamageEffect;

public interface WeaponWielder extends ToolUser<Weapon>, Hostile {
	
	public void addAttack(DamageEffect attack);
	
	public void remAttack(DamageEffect attack);
	
}
