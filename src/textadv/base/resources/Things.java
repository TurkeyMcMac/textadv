package textadv.base.resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import jwmh.dcn.*;

public final class Things {
	
	@SuppressWarnings("unchecked")
	private static Map<Object, Object> getThings() {
		Map<Object, Object> things = null;
		try {
			CapsuleReader reader = new CapsuleReader(
								       new BufferedReader(
								    	   new FileReader("src/resources/things.dcn")));
			things = (Map<Object, Object>)reader.readCapsule();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return things;
	}
	
	public static final Map<Object, Object> THINGS = getThings();
	
	public static Map<String, ?> get(String key) {
		@SuppressWarnings("unchecked")
		Map<String, ?> gotten = (Map<String, ?>)THINGS.get(key);
		return gotten == null ? get("default") : gotten;
	}
	
}
