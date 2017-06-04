package textadv.base.population;

import textadv.base.world.Pile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import textadv.base.damage.DamageEffect;
import textadv.base.damage.DamageType;
import textadv.base.damage.ShortAttack;
import textadv.base.outfits.Weapon;
import textadv.base.outfits.WeaponWielder;

public class Stick extends Pile implements Weapon {
	
	private static final long serialVersionUID = 8341195748466166301L;
	
	int weight = 2;
	WeaponWielder user;
	List<DamageEffect> attacks = new ArrayList<DamageEffect>(Arrays.asList(
		new ShortAttack("Hit", "A firm hit", 5, 5, DamageType.PHYSICAL)
	));
	
	public Stick() {
		super("Stick", "A hard stick.", '|');
	}
	
	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public WeaponWielder getUser() {
		return user;
	}

	@Override
	public void setUser(WeaponWielder user) {
		this.user = user;
	}

	@Override
	public List<DamageEffect> getAttacks() {
		return attacks;
	}

}
