package textadv.base.world;
public class Pile extends Thing {

	private static final long serialVersionUID = -2847910894510039063L;

	public Pile(String name, String info, char icon) {
		super(name, info, icon);
	}
	
	public Pile copy() {
		Pile newPile = new Pile(name, info, icon);
		newPile.setTile(tile);
		return newPile;
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
