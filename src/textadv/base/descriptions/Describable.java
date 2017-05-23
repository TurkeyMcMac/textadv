package textadv.base.descriptions;

public interface Describable {
	
	public String getName();
	
	public String getInfo();
	
	public default String describe() {
		String info = getInfo();
		return getName() + ": " + info;
	}
	
}
