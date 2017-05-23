package textadv.base.damage;

import textadv.base.directions.CarDir;

public class RangeAttack extends Attack {
	
	int range;
	
	public RangeAttack(String name, String info, int damage, int accuracy, int range, DamageType type) {
		super(name, info, accuracy, damage, type);
		this.range = range;
	}
	
	@Override
	protected boolean isInRange(int relAtkX, int relAtkY) {
		return Math.abs(relAtkX) <= range && Math.abs(relAtkY) <= range;
	}
	
	@Override
	protected CarDir checkGeometry(int relAtkX, int relAtkY) {
		return CarDir.directionOf(relAtkX, relAtkY);
	}
}
