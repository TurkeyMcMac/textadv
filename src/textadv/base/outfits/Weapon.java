package textadv.base.outfits;

import java.util.List;

import textadv.base.damage.Attack;
import textadv.base.damage.DamageEffect;
import textadv.base.outfits.Tool;

public interface Weapon extends Tool<WeaponWielder> {
	
	/**
	 * Returns all of this weapon's attacks.
	 * @return the list attacks.
	 */
	public List<DamageEffect> getAttacks();
	
	@Override
	public default void wield(WeaponWielder user) {
		setUser(user);
		for (DamageEffect a : getAttacks()) {
			if (a instanceof Attack) {
				((Attack) a).setUser(user);
			}
			user.addAttack(a);
		}
	}
	
	@Override
	public default void unwield() {
		for (DamageEffect a : getAttacks()) {
			if (a instanceof Attack) {
				((Attack) a).setUser(null);
			}
			getUser().remAttack(a);
		}
		setUser(null);
	}
	
}
