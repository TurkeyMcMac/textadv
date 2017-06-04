package textadv.base.damage;

import java.io.Serializable;

import textadv.base.outfits.Describable;
import textadv.base.outfits.Healthy;

public class DamageEffect implements Describable, Serializable {
	
	private static final long serialVersionUID = -6652181722337993294L;
	
	private String name;
	private String info;
	protected Integer damage;
	protected DamageType type;
	
	public DamageEffect(String name, String info, int damage, DamageType type) {
		this.name = name;
		this.info = info;
		this.damage = damage;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInfo() {
		return info;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public DamageType getType() {
		return type;
	}
	
	public int apply(Healthy subject) {
		return damage / subject.resist(type);
	}
	
}
