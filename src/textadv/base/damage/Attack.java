package textadv.base.damage;

import textadv.base.directions.CarDir;
import textadv.base.outfits.Healthy;
import textadv.base.outfits.Hostile;
import textadv.base.world.Being;
import textadv.base.world.Thing;

public abstract class Attack extends DamageEffect {
	
	int accuracy;
	Hostile user;
	
	public Attack(String name, String info, int accuracy, int damage, DamageType type) {
		super(name, info, damage, type);
		this.accuracy = accuracy;
	}
	
	public void setUser(Hostile user) {
		this.user = user;
	}
	
	protected abstract boolean isInRange(int relAtkX, int relAtkY);
	
	protected abstract CarDir checkGeometry(int relAtkX, int relAtkY);
	
	@Override
	public final int apply(Healthy subject) {
		//relative attack position
		int relAtkX = ((Thing)user).getTile().getX() - ((Being)subject).getTile().getX(),
			relAtkY = ((Thing)user).getTile().getY() - ((Being)subject).getTile().getY();
		if (isInRange(relAtkX, relAtkY) && 
			subject.getDodge().floatValue() * Math.random() < accuracy)
		{
			CarDir atkCarDir = checkGeometry(relAtkX, relAtkY);
			Integer effectiveDamage = new Float (Math.max(0, damage.floatValue() / 
								  				 subject.resist(type)) - 
												 subject.getShield((((Being)subject).isFacing().relative(atkCarDir))))
									  .intValue();
			return effectiveDamage;
		} else {
			return 0;
		}
	}
	
}
