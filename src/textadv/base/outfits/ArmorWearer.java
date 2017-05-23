package textadv.base.outfits;

import textadv.base.directions.RelDir;

public interface ArmorWearer extends PickerUpper {
	
	public boolean wear(Armor armor, RelDir direction);
	
	public boolean unwear(Armor armor);
	
}
