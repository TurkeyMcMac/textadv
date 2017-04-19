
public class Attack extends Concept {
	
	int damage;
	int accuracy;
	DamageType type;
	
	public Attack(String name, String info, int damage, int accuracy, DamageType type) {
		super(name, info);
		this.accuracy = accuracy;
		this.damage = damage;
		this.type = type;
	}
	
	private int calculate(Monster target) {
		return damage / target.resist(type);
	}
	
	public int apply(Monster target) {
		int effectiveDamage = calculate(target);
		target.nowHealth -= effectiveDamage;
		return effectiveDamage;
	}
	
	public int hit(Monster target, int atkX, int atkY) {
		if (target.dodge > accuracy * Math.random()) {
			//relative attack position
			int relAtkX = atkX - target.tile.x,
				relAtkY = atkY - target.tile.y;
			CarDir atkCarDir;
			if (relAtkX >= relAtkY && relAtkX <= -relAtkY) {
				atkCarDir = CarDir.NORTH;
			} else if (relAtkY >= -relAtkX && relAtkY <= relAtkX) {
				atkCarDir = CarDir.EAST;
			} else if (relAtkX >= -relAtkY && relAtkX <= relAtkY) {
				atkCarDir = CarDir.SOUTH;
			} else {
				atkCarDir = CarDir.WEST;
			}
			int effectiveDamage = 0;
			for (RelDir key : target.shields.keySet()) {
				if (target.facing.relative(key) == atkCarDir) {
					effectiveDamage = target.shields.get(key);
				}
			}
			target.nowHealth -= effectiveDamage;
			return effectiveDamage;
		} else {
			return 0;
		}
	}
	
}
