package textadv.base.outfits;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface Describable {
	
	public String getName();
	
	public String getInfo();
	
	public default String describe() {
		String info = getInfo();
		return getName() + ": " + info;
	}
	
	public static String nameList(String label, List<? extends Describable> list) {
		Map<String, Integer> occurrence = new TreeMap<>();
		for (Describable d : list) {
			String name = d.getName();
			Integer occurred = occurrence.get(name);
			if (occurred == null) {
				occurrence.put(name, 1);
			} else {
				occurrence.replace(name, occurred + 1);
			}
		}
		String stringified = label;
		for (String n : occurrence.keySet()) {
			stringified += "\n\t" + n + " x" + occurrence.get(n);
		}
		return stringified;
	}
	
}
