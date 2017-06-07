package textadv.base.outfits;

import java.util.Map;

import textadv.base.resources.Things;

public interface Item extends Describable {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> DEFAULT = (Map<String, Object>)Things.get("item");
	
	public int getWeight();
	
}
