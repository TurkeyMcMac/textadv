package textadv.base.outfits;

public interface PickerUpper extends Describable {
	
	public boolean pickUp(Item item);
	
	public boolean dropOff(Item item);
	
	public void dropAll();
	
}
