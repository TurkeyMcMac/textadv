package textadv.base.damage;

import textadv.base.directions.CarDir;

public class ShortAttack extends Attack {

	public ShortAttack(String name, String info, int accuracy, int damage, DamageType type) {
		super(name, info, accuracy, damage, type);
	}
	
	@Override
	protected boolean isInRange(int relAtkX, int relAtkY) {
		return (relAtkX ==  0 && relAtkY == -1) ||
			   (relAtkX ==  1 && relAtkY ==  0) ||
			   (relAtkX ==  0 && relAtkY ==  1) ||
			   (relAtkX == -1 && relAtkY ==  0);
	}
	
	@Override
	protected CarDir checkGeometry(int relAtkX, int relAtkY) {
		switch (relAtkX) {
			case +1:
				return CarDir.EAST;
			case -1:
				return CarDir.WEST;
			case 0:
				switch (relAtkY) {
					case +1:
						return CarDir.SOUTH;
					case -1:
						return CarDir.NORTH;
					default:
						throw new IllegalArgumentException();	
				}
			default:
				throw new IllegalArgumentException();	
		}
	}
	
}
