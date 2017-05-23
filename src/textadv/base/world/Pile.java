package textadv.base.world;
public class Pile extends Thing {

	public Pile(String name, String info, char icon, Tile tile) {
		super(name, info, icon, tile);
	}
	
	public Pile copy() {
		return new Pile(name, info, icon, tile);
	}
	
	public void drop(Tile tile) {
		tile.drop(this);
	}
	
	public boolean pickUp() {
		return tile.pickUp(this);
	}
	
	public void warp(Tile tile) {
		tile.getGrid().warpPile(this, tile);
	}
	
	public boolean carry(int targX, int targY) {
		return tile.getGrid().carry(this, targX, targY);
	}
}
