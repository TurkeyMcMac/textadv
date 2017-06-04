package textadv.base.world;

import textadv.base.control.Controller;
import textadv.base.directions.CarDir;
import textadv.base.directions.RelDir;

public abstract class Being extends Pile {
	
	private static final long serialVersionUID = -7993888953686955088L;
	
	private CarDir facing;
	protected Controller<?> controller;
	
	public Being(
			String name, 
			String info, 
			char icon,
			CarDir facing, 
			Controller<?> controller) 
	{
		super(name, info, icon);
		this.facing = facing;
		controller.setControlled(this);
		this.controller = controller;
	}
	
	@Override
	protected void doUpdate() {
		controller.control();
	}
	
	public Controller<?> getController() {
		return controller;
	}
	
	public Tile look(int x, int y) {
		return tile.getGrid().getTile(tile.getX() + x, tile.getY() + y);
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
				throw new IllegalArgumentException();
		}
	}
	
	public CarDir isFacing() {
		return facing;
	}
	
	public Tile look(RelDir r) {
		return look(facing.relative(r));
	}
	
	public Tile look() {
		return look(RelDir.FORWARD);
	}
	
	public boolean hop(int targX, int targY) {
		return carry(tile.getX() + targX, tile.getY() + targY);
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
				throw new IllegalArgumentException();
		}
	}
	
	public boolean step(RelDir r) {
		return step(facing.relative(r));
	}
	
	public boolean step() {
		return step(RelDir.FORWARD);
	}
	
}
