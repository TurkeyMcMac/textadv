package textadv.base.factions;

import java.util.Map;
import java.util.TreeMap;

import jwmh.dcn.Capsules;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import textadv.base.outfits.Describable;

public class Faction implements Describable, Serializable {
	
	private static final long serialVersionUID = -3988592444744145319L;
	
	private String name;
	private String info;
	private int defaultRelationship;
	private Map<Faction, Integer> relationships;
	
	private static Map<String, Faction> allFactions = new TreeMap<>();
	
	@SuppressWarnings("unchecked")
	public static void loadAll(String path) {
		List<Object> factions = null;
		try {
			factions = Capsules.readListFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Object of : factions) {
			Map<String, Object> f = (Map<String, Object>)of;
			String name = (String)f.get("name");
			if (!allFactions.containsKey(name)) {
				new Faction(name, (String)f.get("info"), (Integer)f.get("upon-meeting"));
			}
		}
		for (Object of : factions) {
			Map<String, Object> f = (Map<String, Object>)of;
			Map<String, Integer> relationships = ((Map<String, Integer>)f.get("relationships"));
			for (String key : relationships.keySet()) {
				allFactions.get(f.get("name")).teach(allFactions.get(key), relationships.get(key));
			}
		}
	}
	
	public static void writeAll(String path) {
		List<Map<String, Object>> factionInfos = new ArrayList<>();
		for (String n : allFactions.keySet()) {
			Faction faction = allFactions.get(n);
			Map<String, Object> factionInfo = new LinkedHashMap<>();
			factionInfo.put("name", faction.name);
			factionInfo.put("info", faction.info);
			factionInfo.put("upon-meeting", faction.defaultRelationship);
			Map<String, Integer> relsByName = new TreeMap<>();
			for (Faction f : faction.relationships.keySet()) {
				relsByName.put(f.name, faction.relationships.get(f));
			}
			factionInfo.put("relationships", relsByName);
			factionInfos.add(factionInfo);
		}
		try {
			Capsules.writeFile(path, factionInfos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, Faction> getAll() {
		return allFactions;
	}
	
	public static void setAll(Map<String, Faction> allFactions) {
		Faction.allFactions = allFactions;
	}
	
	public static Faction get(String name) {
		return allFactions.get(name);
	}
	
	public Faction(String name, String info, int defaultRelationship) {
		this.name = name;
		this.info = info;
		this.defaultRelationship = defaultRelationship;
		this.relationships = new HashMap<Faction, Integer>();
		allFactions.put(name, this);
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
