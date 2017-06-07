package textadv.base.resources;

import java.io.IOException;
import java.util.Map;
import jwmh.dcn.*;

public final class Things {
	
	private static Map<Object, Object> getThings() {
		try {
			return Capsules.readHashFile("src/resources/things");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static final Map<Object, Object> THINGS = getThings();
	
	public static Object get(String key) {
		Object gotten = THINGS.get(key);
		return gotten == null ? THINGS.get("default") : gotten;
	}
	
}
