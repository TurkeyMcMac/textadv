
public class Pile extends Thing {

	public Pile(String name, String info, Tile tile) {
		super(name, info, tile);
	}
	
	public Pile copy() {
		return new Pile(name, info, tile);
	}
	
	public void place(Tile tile) {
		tile.place(this);
	}
	
	public boolean unplace() {
		return this.tile.unplace(this);
	}
	
	public boolean shift(int targX, int targY) {
		return tile.grid.shift(this, targX, targY);
	}
}
