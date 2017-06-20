package textadv.base.population;

import java.util.Map;

import textadv.base.outfits.Item;
import textadv.base.resources.Things;
import textadv.base.world.Pile;

public class LoremIpsum extends Pile implements Item {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	private static final Map<String, ?> RESOURCES = (Map<String, ?>)Things.get("lorem-ipsum");
	
	private int weight = 1;
	
	public LoremIpsum() {
		super((String)RESOURCES.get("name"), (String)RESOURCES.get("info"), (Character)RESOURCES.get("icon"));
	}
	
	public int getWeight() {
		return weight;
	}
	
}
