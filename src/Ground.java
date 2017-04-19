
public class Ground extends Thing implements Graphic {
	
	char icon;
	
	public Ground(String name, String info, char icon, Tile tile) {
		super(name, info, tile);
		this.icon = icon;
	}
	
	public char getIcon() {
		return icon;
	}
	
	public Ground copy() {
		return new Ground(name, info, icon, tile);
	}
	
	public static Ground empty(Tile tile) {
		return new Ground("empty", "a lack of ground", '_', tile);
	}
	
	public static Ground empty() {
		return empty(null);
	}
	
	public Ground place(Tile tile) {
		return tile.place(this);
	}
	
	public Ground unplace() {
		return this.tile.unplace();
	}
	
	public Ground shift(int targX, int targY) {
		return tile.grid.shift(this, targX, targY);
	}

}
