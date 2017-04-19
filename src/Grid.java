
import java.util.function.Consumer;
import java.util.ArrayList;

public class Grid {

	int width;
	int height;
	Tile filler;
	Tile[][] tiles;
	int tick = 0;
	ArrayList<Tile> loaded = new ArrayList<Tile>();
	private String map;
	private int refLine;
	
	public Grid(int width, int height, Tile filler) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				place(filler.copy(), i, j);
			}
		}
	}
	
	public Tile spot(int x, int y) {
		x = Math.min(Math.max(x, 0), width - 1);
		y = Math.min(Math.max(y, 0), height - 1);
		return tiles[x][y];
	}
	
	public Tile place(Tile tile, int x, int y) {
		tile.changeGrid(this);
		tile.changePos(x, y);
		Tile oldTile = tiles[x][y];
		tiles[x][y] = tile;
		return oldTile;
	}
	
	public Tile unplace(Tile tile) {
		tile.changeGrid(null);
		return place(Tile.empty(), tile.x, tile.y);
	}
	
	public Tile shift(Tile tile, int targX, int targY) {
		if (targX >= 0 && targX < width && targY >= 0 && targY < height) {
			return place(unplace(tile), targX, targY);
		}
		return tile;
	}

	public ArrayList<Tile> load(int x1, int y1, int x2, int y2) {
		if (x1 < 0) {
			x1 = 0;
		}
		if (y1 < 0) {
			y1 = 0;
		}
		if (x2 > width - 1) {
			x2 = width - 1;
		}
		if (y2 > height - 1) {
			y2 = height - 1;
		}
		for(int i = y1; i <= y2; i++) {
			for(int j = x1; j <= x2; j++) {
				loaded.add(tiles[j][i]);
			}
		}
		return loaded;
	}
	
	private ArrayList<Tile> applyToLoaded (Consumer<Tile> f) {
		for (Tile t : loaded) {
			f.accept(t);
		}
		return loaded;
	}
	
	private ArrayList<Tile> applyToLoaded (int x1, int y1, int x2, int y2, Consumer<Tile> f) {
		load(x1, y1, x2, y2);
		return applyToLoaded(f);
	}
	
	private final Consumer<Tile> DRAW = (t) -> {
		char icon = t.ground.icon;
		for (Pile p : t.piles) {
			if (p instanceof Graphic) {
				icon = ((Graphic) p).getIcon();
				break;
			}
		}
		int y = t.y;
		if (y > refLine) {
			map += '\n';
			refLine = y;
		}
		map = map + icon + ' ';
	};
	
	public String draw() {
		map = "";
		refLine = loaded.get(0).y;
		applyToLoaded(DRAW);
		return map;
	}
	
	public String draw(int x1, int y1, int x2, int y2) {
		map = "";
		refLine = loaded.get(0).y;
		applyToLoaded(x1, y1, x2, y2, DRAW);
		return map;
	}
	
	public ArrayList<Tile> update() {
		tick = (tick + 1) % Integer.MAX_VALUE;
		return applyToLoaded((t) -> {
			t.update();
		});
	}
	
	public void place(Pile pile, int x, int y) {
		Tile tile = tiles[x][y];
		tile.place(pile);
	}
	
	public void unplace(Pile pile) {
		pile.tile.unplace(pile);
	}
	
	public Ground place(Ground ground, int x, int y) {
		Tile tile = tiles[x][y];
		return tile.place(ground);
	}
	
	public Ground unplace(Ground ground) {
		return ground.tile.unplace();
	}
	
	public boolean shift(Pile pile, int targX, int targY) {
		if (targX >= 0 && targX < width && targY >= 0 && targY < height) {
			Tile tile = tiles[targX][targY];
			boolean isSolid = tile.isSolid();
			if (!isSolid) {
				Tile targTile = tile;
				unplace(pile);
				targTile.place(pile);
			}
			return isSolid;
		}
		return false;
	}
	
	public Ground shift(Ground ground, int targX, int targY) {
		if (targX >= 0 && targX < width && targY >= 0 && targY < height) {
			Tile targTile = tiles[targX][targY];
			ground.tile.unplace();
			return targTile.place(ground);
		}
		return ground;
	}
	
	public static void main(String[] args) {
		Grid grid = new Grid(5, 5, Tile.empty());
		Ground ground = new Ground("Ground", "Some ground.", '#', null);
		Ground grund = new Ground("Ground", "Some ground.", '@', null);
		Foo foo = new Foo(CarDir.NORTH, null);
		grid.place(grund, 1, 1);
		grid.place(ground, 3, 2);
		grid.place(foo, 3, 3);
		grid.load(0, 0, 4, 4);
		System.out.println(grid.draw());
		for (int i = 0; i < 5; i++) {
			grid.update();
		}
		System.out.println();
		System.out.println(grid.draw());
	}

}
