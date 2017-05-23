package textadv.base.directions;

public enum CarDir {
	
	NORTH, EAST, SOUTH, WEST;
	
	public CarDir relative(RelDir r) {
		return CarDir.values()[(ordinal() + r.ordinal()) % 4];
	}
	
	public RelDir relative(CarDir c) {
		int spot = c.ordinal() - ordinal();
		if(spot < 0) {
			spot += 4;
		}
		return RelDir.values()[spot];
	}
	
	public static CarDir directionOf(int relativeX, int relativeY) {
		if (relativeX >= relativeY && relativeX <= -relativeY) {
			return CarDir.NORTH;
		} else if (relativeY >= -relativeX && relativeY <= relativeX) {
			return CarDir.EAST;
		} else if (relativeX >= -relativeY && relativeX <= relativeY) {
			return CarDir.SOUTH;
		} else {
			return CarDir.WEST;
		}
	}
	
}