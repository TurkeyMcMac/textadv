package textadv.base.outfits;

public interface Armor extends Item {
	
	public ArmorWearer getUser();
	
	public void wear(ArmorWearer user);
	
	public void unwear();
	
	public int getArmor();
	
}
