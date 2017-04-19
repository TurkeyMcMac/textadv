
public abstract class Thing extends Concept {
	
	Tile tile;
	
	public Thing(String name, String info, Tile tile) {
		super(name, info);
		this.tile = tile;
	}
	
	abstract public Thing copy();
	
	public void update() {}
	
	public Tile getTile() {
		return tile;
	}
	
	Tile changeTile(Tile tile) {
		Tile oldTile = this.tile;
		this.tile = tile;
		return oldTile;
	}
	
}
