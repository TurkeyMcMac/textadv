package textadv.base.world;

import textadv.base.outfits.Describable;

public abstract class Thing implements Describable {
	
	protected String name;
	protected String info;
	protected Tile tile;
	private boolean updated = false;
	protected char icon;

	public Thing(String name, String info, char icon) {
		this.name = name;
		this.info = info;
		this.icon = icon;
	}
	
	abstract public Thing copy();
	
	final public void update() {
		if (!updated) {
			doUpdate();
			updated = true;
		}
	}
	
	final public void resetUpdater() {
		updated = false;
	}
	
	protected void doUpdate() {}
	
	public String getName() {
		return name;
	}
	
	public String getInfo() {
		return info;
	}
	
	public String toString() {
		return describe();
	}
	
	public char getIcon() {
		return icon;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
}
