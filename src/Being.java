
import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class Being extends Pile {
	
	CarDir facing;
	int tick = 0;
	ArrayList<Consumer<Being>> sequence;
	
	public Being(
			String name, 
			String info, 
			CarDir facing, 
			ArrayList<Consumer<Being>> sequence, 
			Tile tile) 
	{
		super(name, info, tile);
		this.facing = facing;
		this.sequence = sequence;
	}
	
	public void update() {
		control();
	}
	
	public void swapLine (int line) {
		tick = line - 1;
	}
	
	public Tile look(int x, int y) {
		return tile.grid.spot(x, y);
	}
	
	public Tile look(CarDir c) {
		switch (c) {
			case NORTH:
				return look(0, -1);
			case EAST:
				return look(+1, 0);
			case SOUTH:
				return look(0, +1);
			case WEST:
				return look(-1, 0);
			default:
				return tile;
		}
	}
	
	public Tile look(RelDir r) {
		return look(facing.relative(r));
	}
	
	public Tile look() {
		return look(RelDir.FORWARD);
	}
	
	public boolean hop(int targX, int targY) {
		return shift(tile.x + targX, tile.y + targY);
	}
	
	public void turn(RelDir r) {
		facing = facing.relative(r);
	}
	
	public void turn(CarDir c) {
		facing = c;
	}
	
	public boolean step(CarDir c) {
		switch (c) {
			case NORTH:
				return hop(0, -1);
			case EAST:
				return hop(+1, 0);
			case SOUTH:
				return hop(0, +1);
			case WEST:
				return hop(-1, 0);
			default:
				return false;
		}
	}
	
	public boolean step(RelDir r) {
		return step(facing.relative(r));
	}
	
	public boolean step() {
		return step(RelDir.FORWARD);
	}
	
	public void control() {
		if (tick < sequence.size()) {
			sequence.get(tick).accept(this);
			tick++;
		}
	}
	
}
