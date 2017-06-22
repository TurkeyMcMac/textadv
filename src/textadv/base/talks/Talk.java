package textadv.base.talks;

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jwmh.dcn.CapsuleReader;
import textadv.base.resources.Things;

public class Talk implements Serializable {
	
	private static final long serialVersionUID = 2565840660480700392L;
	
	private String text;
	private List<Talk> results = new ArrayList<>();
	private List<String> resultNames = new ArrayList<>();
	
	public Talk(String text, Map<String, Talk> nextTalks) {
		this.text = text;
		for (Map.Entry<String, Talk> p : nextTalks.entrySet()) {
			resultNames.add(p.getKey());
			results.add(p.getValue());
		}
	}
	
	public Talk(List<?> structure) {
		text = (String)structure.get(0);
		@SuppressWarnings("unchecked")
		Map<String, List<?>> nextTalks = (Map<String, List<?>>)structure.get(1);
		for (Map.Entry<String, List<?>> p : nextTalks.entrySet()) {
			resultNames.add(p.getKey());
			results.add(new Talk(p.getValue()));
		}
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
		StringBuffer stringified = new StringBuffer(text);
		for (int i = 0; i < resultNames.size(); i++) {
			stringified.append("\n(" + i + ") " + resultNames.get(i));
		}
		return stringified.toString();
	}
	
	public static Map<String, Talk> nexts(Object ... nodes) {
		Map<String, Talk> nodeHash = new HashMap<>();
		for (int i = 1; i < nodes.length; i += 2) {
			nodeHash.put((String)nodes[i - 1], (Talk)nodes[i]);
		}
		return nodeHash;
	}
	
	public static void main(String[] args) {
		Talk test = new Talk((List<?>)Things.get("legged-fish").get("talk"));
		System.out.println(test.pick(0).pick(0));
	}
	
}
