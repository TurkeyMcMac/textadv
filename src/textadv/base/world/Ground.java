package textadv.base.world;

import java.util.Map;
import textadv.base.resources.Things;

public class Ground extends Thing {
	
	private static final long serialVersionUID = 555347518928955631L;

	public Ground(String name, String info, char icon) {
		super(name, info, icon);
	}
	
	public Ground copy() {
		Ground newGround = new Ground(name, info, icon);
		newGround.setTile(tile);
		return newGround;
	}
	
	@SuppressWarnings("unchecked")
	private static final Map<String, Object> RESOURCES = (Map<String, Object>)Things.get("empty");
	
	public static Ground empty() {
		return new Ground((String)RESOURCES.get("name"), (String)RESOURCES.get("info"), (Character)RESOURCES.get("icon"));
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
