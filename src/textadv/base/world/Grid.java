
package textadv.base.world;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

import textadv.base.outfits.Solid;
import textadv.base.player.Player;

public class Grid {

	public final int WIDTH;
	public final int HEIGHT;
	Tile[][] tiles;
	private int tick = 0;
	private List<Tile> loaded = new ArrayList<Tile>();
	private String map;
	private int refLine;
	private Player player;
	
	public Grid(int width, int height, Tile filler) {
		WIDTH = width;
		HEIGHT = height;
		this.tiles = new Tile[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				setTile(filler.copy(), i, j);
			}
		}
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Tile getTile(int x, int y) {
		x = Math.min(Math.max(x, 0), WIDTH - 1);
		y = Math.min(Math.max(y, 0), HEIGHT - 1);
		return tiles[x][y];
	}
	
	public Tile setTile(Tile tile, int x, int y) {
		tile.setGrid(this);
		tile.setPos(x, y);
		Tile oldTile = tiles[x][y];
		tiles[x][y] = tile;
		return oldTile;
	}
	
	public Tile removeTile(Tile tile) {
		tile.setGrid(null);
		return setTile(Tile.empty(), tile.getX(), tile.getY());
	}
	
	public Tile shift(Tile tile, int targX, int targY) {
		if (targX >= 0 && targX < WIDTH && targY >= 0 && targY < HEIGHT) {
			return setTile(removeTile(tile), targX, targY);
		}
		return tile;
	}
	
	public void warpPile(Pile pile, Tile tile) {
		pile.pickUp();
		tile.drop(pile);
	}
	
	public Ground warpGround(Ground ground, Tile tile) {
		ground.unplace();
		return tile.place(ground);
	}
	
	public void load(int x1, int y1, int x2, int y2) {
		if (x1 < 0) {
			x1 = 0;
		}
		if (y1 < 0) {
			y1 = 0;
		}
		if (x2 > WIDTH - 1) {
			x2 = WIDTH - 1;
		}
		if (y2 > HEIGHT - 1) {
			y2 = HEIGHT - 1;
		}
		loaded = new ArrayList<Tile>();
		for(int i = y1; i <= y2; i++) {
			for(int j = x1; j <= x2; j++) {
				loaded.add(tiles[j][i]);
			}
		}
	}
	
	private void applyToLoaded(Consumer<Tile> f) {
		for (Tile t : loaded) {
			f.accept(t);
		}
	}
	
	private void applyToLoaded(int x1, int y1, int x2, int y2, Consumer<Tile> f) {
		load(x1, y1, x2, y2);
		applyToLoaded(f);
	}
	
	private final Consumer<Tile> DRAW = (t) -> {
		char icon = t.getGround().getIcon();
		for (Pile p : t.getPiles()) {
			icon = p.getIcon();
			if (p instanceof Being) {	
				break;
			}
		}
		int y = t.getY();
		if (y > refLine) {
			map += '\n';
			refLine = y;
		}
		map = map + icon;
	};
	
	public String draw() {
		map = new String();
		refLine = loaded.get(0).getY();
		applyToLoaded(DRAW);
		return map;
	}

	public String draw(int x1, int y1, int x2, int y2) {
		map = "";
		refLine = loaded.get(0).getY();
		applyToLoaded(x1, y1, x2, y2, DRAW);
		return map;
	}
	
	public void loadPlayer(int xReach, int yReach) {
		Tile playerTile = player.getTile();
		int playerX = playerTile.getX();
		int playerY = playerTile.getY();
		load(playerX - xReach, playerY - yReach, playerX + xReach, playerY + yReach);
	}
	
	public void update() {
		tick = (tick + 1) % Integer.MAX_VALUE;
		applyToLoaded((t) -> t.resetUpdater());
		applyToLoaded((t) -> t.update());
	}
	
	public void drop(Pile pile, int x, int y) {
		Tile tile = tiles[x][y];
		tile.drop(pile);
	}
	
	public void pickUp(Pile pile) {
		pile.tile.pickUp(pile);
	}
	
	public Ground place(Ground ground, int x, int y) {
		Tile tile = tiles[x][y];
		return tile.place(ground);
	}
	
	public Ground unplace(Ground ground) {
		return ground.tile.unplace();
	}
	
	public boolean carry(Pile pile, int targX, int targY) {
		Tile tile = getTile(targX, targY);
		if (!(tile.isSolid() && pile instanceof Solid)) {
			pile.warp(tile);
			return true;
		}
		return false;
	}
	
	public Ground shuffle(Ground ground, int targX, int targY) {
		return ground.warp(getTile(targX, targY));
	}

}
