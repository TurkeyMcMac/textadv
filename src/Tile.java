
import java.util.*;

public class Tile {
	
	Ground ground;
	ArrayList<Pile> piles;
	int x;
	int y;
	Grid grid;
	
	public Tile(Ground ground, ArrayList<Pile> piles, int x, int y, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		ground.changeTile(this);
		this.ground = ground;
		this.piles = piles;
		for(Pile p: piles) {
			p.changeTile(this);
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
	
	public static Tile empty(int x, int y, Grid grid) {
		Ground ground = Ground.empty();
		Tile emptyTile = new Tile(ground, new ArrayList<Pile>(), x, y, grid);
		return emptyTile;
	}
	
	public static Tile empty() {
		return empty(0, 0, null);
	}
	
	public void changeGrid(Grid grid) {
		this.grid = grid;
	}
	
	public void changePos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		ground.update();
		for (int i = 0; i < piles.size(); i++) {
			piles.get(i).update();
		}
	}
	
	public Tile shift(int targX, int targY) {
		return grid.shift(this, targX, targY);
	}
	
	public Ground place(Ground ground) {
		Ground oldGround = this.ground;
		ground.changeTile(this);
		this.ground = ground;
		return oldGround;
	}
	
	public Ground unplace() {
		ground.changeTile(null);
		return place(Ground.empty());
	}
	
	public void place(Pile pile) {
		pile.changeTile(this);
		piles.add(pile);
	}
	
	public boolean unplace(Pile pile) {
		pile.changeTile(null);
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
