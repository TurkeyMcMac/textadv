package textadv.base.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jwmh.dcn.*;

public final class Resources {
	
	private static Map<Object, Object> getHashResource(String resourceFile) {
		try {
			return Capsules.readHashFile("src/resources/" + resourceFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static List<Object> getListResource(String resourceFile) {
		try {
			return Capsules.readListFile("src/resources/" + resourceFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static final Map<String, Object> RESOURCES=new HashMap<>();
	
	static {
		RESOURCES.put("things", getHashResource("things"));
		RESOURCES.put("factions", getListResource("factions"));
	}
	
	public static Object get(String key, String category) {
		@SuppressWarnings("unchecked")
		Map<Object, Object> resource = (Map<Object, Object>)RESOURCES.get(category);
		Object gotten = resource.get(key);
		return gotten == null ? resource.get("default") : gotten;
	}
	
}
