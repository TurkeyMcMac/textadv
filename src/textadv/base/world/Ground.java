package textadv.base.world;

import java.util.Map;
import textadv.base.resources.Resources;

public class Ground extends Thing {
	
	public Ground(String name, String info, char icon, Tile tile) {
		super(name, info, icon, tile);
	}
	
	public Ground copy() {
		return new Ground(name, info, icon, tile);
	}
	
	@SuppressWarnings("unchecked")
	private static final Map<String, Object> RESOURCES = (Map<String, Object>)Resources.get("empty", "things");
	
	public static Ground empty(Tile tile) {
		return new Ground((String)RESOURCES.get("name"), (String)RESOURCES.get("info"), (Character)RESOURCES.get("icon"), tile);
	}
	
	public static Ground empty() {
		return empty(null);
	}
	
	public Ground place(Tile tile) {
		return tile.place(this);
	}
	
	public Ground unplace() {
		return this.tile.unplace();
	}
	
	public Ground warp(Tile tile) {
		return this.tile.getGrid().warpGround(this, tile);
	}
	
	public Ground shift(int targX, int targY) {
		return tile.getGrid().shuffle(this, targX, targY);
	}

}
