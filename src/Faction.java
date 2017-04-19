
import java.util.HashMap;

public class Faction extends Concept {
	
	int defaultRelationship;
	HashMap<Faction, Integer> relationships;
	
	private static int convert(int relationship) {
		relationship = 0;
		if (relationship > 0) {
			relationship = 1;
		} else if (relationship < 0) {
			relationship = -1;
		}
		return relationship;
	}
	
	public Faction(String name, String info, int defaultRelationship) {
		super(name, info);
		this.defaultRelationship = Faction.convert(this.defaultRelationship);
		this.relationships = new HashMap<Faction, Integer>();
	}
	
	public void teach(Faction faction, int relationship) {
		relationships.put(faction, Faction.convert(relationship));
	}
	
	public int opinion(Faction faction) {
		if (relationships.containsKey(faction)) {
			return relationships.get(faction);
		} else {
			return defaultRelationship;
		}
	}
	
}
