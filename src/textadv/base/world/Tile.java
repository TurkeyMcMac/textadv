package textadv.base.world;

import java.util.List;

import textadv.base.outfits.Solid;

import java.util.ArrayList;

public final class Tile {
	
	private Ground ground;
	private List<Pile> piles;
	private int x;
	private int y;
	private Grid grid;
	
	public Tile(Ground ground, ArrayList<Pile> piles, int x, int y, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		ground.tile = this;
		this.ground = ground;
		this.piles = piles;
		for(Pile p: piles) {
			p.tile = this;
		}
	}
	
	public Tile copy() {
		Tile copied = new Tile(ground.copy(), new ArrayList<Pile>(), x, y, grid);
		for(int i = 0; i < piles.size(); i++) {
			Pile pile = this.piles.get(i).copy();
			pile.tile = copied;
			copied.piles.set(i, pile);
		}
		return copied;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Ground getGround() {
		return ground;
	}
	
	public List<Pile> getPiles() {
		return piles;
	}
	
	public static Tile empty(int x, int y, Grid grid) {
		Ground ground = Ground.empty();
		Tile emptyTile = new Tile(ground, new ArrayList<Pile>(), x, y, grid);
		return emptyTile;
	}
	
	public static Tile empty() {
		return empty(0, 0, null);
	}
	
	void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		ground.update();
		for (int i = 0; i < piles.size(); i++) {
			piles.get(i).update();
		}
	}
	
	public void resetUpdater() {
		ground.resetUpdater();
		for (int i = 0; i < piles.size(); i++) {
			piles.get(i).resetUpdater();
		}
	}
	
	public Tile shift(int targX, int targY) {
		return grid.shift(this, targX, targY);
	}
	
	@Deprecated
	void place(Thing thing) {}
	
	public Ground place(Ground ground) {
		Ground oldGround = this.ground;
		ground.tile = this;
		this.ground = ground;
		return oldGround;
	}
	
	public Ground unplace() {
		ground.tile = null;
		return place(Ground.empty());
	}
	
	public void drop(Pile pile) {
		pile.tile = this;
		piles.add(pile);
	}
	
	public boolean pickUp(Pile pile) {
		pile.tile = null;
		return piles.remove(pile);
	}
	
	public boolean isSolid() {
		boolean isSolid = ground instanceof Solid;
		if (!isSolid) {
			for (Pile p : piles) {
				if (p instanceof Solid) {
					isSolid = true;
					break;
				}
			}
		}
		return isSolid;
	}
	
}
