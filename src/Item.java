
public class Item extends Pile implements Graphic {
	
	char icon;
	int weight;
	
	public Item(String name, String info, char icon, int weight, Tile tile) {
		super(name, info, tile);
		this.icon = icon;
		this.weight = weight;
	}
	
	public char getIcon() {
		return icon;
	}
	
	public int getWeight() {
		return weight;
	}
	
}
