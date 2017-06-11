package textadv.base.outfits;

import java.util.Map;

import jwmh.misc.Pair;
import textadv.base.damage.DamageEffect;

public interface WeaponWielder extends ToolUser<Weapon>, Hostile {
	
	public Map<String, Pair<DamageEffect, Integer>> getAttacksFull();
	
	public default void addAttack(DamageEffect attack) {
		Map<String, Pair<DamageEffect, Integer>> attacks = getAttacksFull();
		String name = attack.getName();
		attacks.put(
			attack.getName(),
			new Pair<>(
				attack,
				attacks.containsKey(name) ?
					attacks.get(name).end + 1 :
					1));
	}
	
	public default void remAttack(DamageEffect attack) {
		Map<String, Pair<DamageEffect, Integer>> attacks = getAttacksFull();
		Pair<DamageEffect, Integer> value = attacks.get((attack.getName()));
		int times = value == null ? 0 : value.end;
		if (times > 1)
			attacks.replace(attack.getName(), new Pair<>(attack, times  - 1));
		else
			attacks.remove(attack.getName());
	}
	
}
