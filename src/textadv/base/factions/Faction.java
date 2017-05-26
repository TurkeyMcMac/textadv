package textadv.base.factions;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import textadv.base.outfits.Describable;
import textadv.base.resources.Resources;

public class Faction implements Describable {
	
	String name;
	String info;
	int defaultRelationship;
	Map<Faction, Integer> relationships;
	
	private static final Map<String, Faction> FACTIONS = new HashMap<>();
	
	static {
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> factions = (List<Map<String, Object>>)Resources.RESOURCES.get("factions");
		for (Map<String, Object> f : factions) {
			new Faction((String)f.get("name"), (String)f.get("info"), (Integer)f.get("upon-meeting"));
		}
		for (Map<String, Object> f : factions) {
			@SuppressWarnings("unchecked")
			Map<String, Integer> relationships = ((Map<String, Integer>)f.get("relationships"));
			for (String key : relationships.keySet()) {
				FACTIONS.get(f.get("name")).teach(FACTIONS.get(key), relationships.get(key));
			}
		}
	}
	
	public static Faction get(String name) {
		return FACTIONS.get(name);
	}
	
	public Faction(String name, String info, int defaultRelationship) {
		this.name = name;
		this.info = info;
		this.defaultRelationship = defaultRelationship;
		this.relationships = new HashMap<Faction, Integer>();
		FACTIONS.put(name, this);
	}
	
	public String getName() {
		return name;
	}
	
	public String getInfo() {
		return info;
	}
	
	@Override
	public String toString() {
		String stringified = describe()
				  		+ "\nfriendliness: " + defaultRelationship
				  		+ "\ncurrent relationships:";
		if (!relationships.isEmpty()) {
			for (Faction f : relationships.keySet()) {
				stringified += "\n\t" + f.getName() + ": " + relationships.get(f);
			}
		} else {
			stringified += "\n\tnone";
		}
		return stringified;
	}
	
	public void teach(Faction faction, int relationship) {
		relationships.put(faction, relationship);
	}
	
	public int opinion(Faction faction) {
		if (relationships.containsKey(faction)) {
			return relationships.get(faction);
		} else {
			return defaultRelationship;
		}
	}
	
}
