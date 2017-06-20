
package textadv.base.world;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import textadv.base.outfits.Solid;
import textadv.base.player.Player;

public class Grid implements Serializable {

	private static final long serialVersionUID = 4473089899578619072L;
	
	public final int WIDTH;
	public final int HEIGHT;
	Tile[][] tiles;
	private transient List<Tile> loaded = new ArrayList<>();
	private Player player;
	private StringBuffer alerts = new StringBuffer();
	
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
	
	public List<Tile> select(int x1, int y1, int x2, int y2) {
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
		List<Tile> selected = new ArrayList<>();
		for(int i = y1; i <= y2; i++) {
			for(int j = x1; j <= x2; j++) {
				selected.add(tiles[j][i]);
			}
		}
		return selected;
	}
	
	public void load(int x1, int y1, int x2, int y2) {
		loaded = select(x1, y1, x2, y2);
	}
	
	public String draw() {
		String map = "";
		int refLine = loaded.get(0).getY();
		for (Tile t : loaded) {
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
		return map;
	}
	
	public void loadTile(Tile tile, int xReach, int yReach) {
		int x = tile.getX(),
			y = tile.getY();
		load(x - xReach, y - yReach, x + xReach, y + yReach);
	}
	
	public void loadPlayer(int xReach, int yReach) {
		loadTile(player.getTile(), xReach, yReach);
	}
	
	public void update() {
		alerts.delete(0, alerts.length());
		for (Tile t : loaded) t.resetUpdater();
		for (Tile t : loaded) t.update();
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
	
	public void alert(Object message) {
		alerts.append('\n' + message.toString());
	}
	
	public String getAlerts() {
		return alerts.toString();
	}
	
}
