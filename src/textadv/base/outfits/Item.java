package textadv.base.outfits;

import java.util.Map;

import textadv.base.resources.Resources;

public interface Item extends Describable {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> DEFAULT = (Map<String, Object>)Resources.get("item", "things");
	
	public int getWeight();
	
}
