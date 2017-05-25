package textadv.base.talks;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Talk {
	
	private String text;
	private List<Talk> results;
	private List<String> resultNames;
	
	public Talk(String text, LinkedHashMap<String, Talk> nextTalks) {
		this.text = text;
		this.results = new ArrayList<>(nextTalks.values());
		this.resultNames = new ArrayList<>(nextTalks.keySet());
	}
	
	public Talk pick(int index) {
		if (index >= 0 && index < results.size()) {
			Talk result = results.get(index);
			result.picked();
			return result;
		}
		return null;
	}
	
	protected void picked() {}
	
	@Override
	public String toString() {
		String stringified = text;
		for (int i = 0; i < resultNames.size(); i++) {
			stringified += "\n(" + i + ") " + resultNames.get(i);
		}
		return stringified;
	}
	
	public static LinkedHashMap<String, Talk> nexts(Object ... nodes) {
		LinkedHashMap<String, Talk> nodeHash = new LinkedHashMap<>();
		for (int i = 1; i < nodes.length; i += 2) {
			nodeHash.put((String)nodes[i - 1], (Talk)nodes[i]);
		}
		return nodeHash;
	}
	
}
