package textadv.base.outfits;

import java.util.Map;

import textadv.base.resources.Resources;
import textadv.base.world.Tile;

public interface Item {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> DEFAULT = (Map<String, Object>)Resources.get("item", "things");
	
	public int getWeight();
	
	public Tile getTile();
	
}
