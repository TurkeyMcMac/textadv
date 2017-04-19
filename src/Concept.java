
public abstract class Concept {
	
	String name;
	String info;
	
	protected Concept(String name, String info) {
		this.name = name;
		this.info = info;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInfo() {
		return info;
	}
	
	public String describe() {
		return name + " - " + info;
	}

}
