
public enum CarDir {
	
	NORTH, EAST, SOUTH, WEST;
	
	public CarDir relative(RelDir r) {
		return CarDir.values()[(ordinal() + r.ordinal()) % 4];
	}
	
}