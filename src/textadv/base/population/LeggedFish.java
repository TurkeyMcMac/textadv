package textadv.base.population;

import java.util.Map;

import textadv.base.control.ConstantController;
import textadv.base.directions.CarDir;
import textadv.base.outfits.Item;
import textadv.base.outfits.PickerUpper;
import textadv.base.outfits.Talker;
import textadv.base.plot.Quest;
import textadv.base.resources.Things;
import textadv.base.talks.EffectTalk;
import textadv.base.talks.EndTalk;
import textadv.base.talks.Talk;
import textadv.base.world.Being;
import textadv.base.world.Grid;
import textadv.base.world.Pile;
import textadv.base.world.Tile;

public class LeggedFish extends Being implements Item, PickerUpper, Talker {
	
	private static final long serialVersionUID = -3232710453541746256L;
	
	private Talk questStart = new EffectTalk(
			"Would you mind picking me up, "
			+ "carrying me to the coordinates (10, 10), "
			+ "and dropping me off?",
			Talk.nexts("Sure!", new EndTalk("Good.", this)), this, (t) -> { ((LeggedFish)t).bringToPlace.start(); });
	private Talk nowTalk;
	private boolean talking;
	private Talk talk = new Talk("Hi, I'm a fish thing!",
			Talk.nexts(
					"What?", questStart,
					"Hello", questStart));
	private int weight = 3;
	private Grid grid;
	
	public LeggedFish(CarDir facing, Grid grid) {
		super((String)RESOURCES.get("name"),
			  (String)RESOURCES.get("info"),
			  (Character)RESOURCES.get("icon"),
			  facing, LeggedFish.controller);
		this.grid = grid;
	}
	
	@SuppressWarnings("unchecked")
	private static final Map<String, Object> RESOURCES = (Map<String, Object>) Things.get("legged-fish");
	
	private static ConstantController controller = new ConstantController(
		(c) -> {
			Tile tile = c.getControlled().getTile();
			if (tile.getX() == 10 && tile.getY() == 10) {
				((LeggedFish)c.getControlled()).bringToPlace.finish();
			}
		}
	);
	
	private Quest bringToPlace = new Quest("Fish Investigation", grid) {

		private static final long serialVersionUID = 6426460979917231988L;

		@Override
		protected void doStart() {
			grid.alert("Bring the fish to its desired position.\n"
					+ "(Check your coordinates by typing \"look h\")");
		}

		@Override
		protected void doFinish() {
			dropOff(new LoremIpsum());
			pickUp();
			grid.alert("before it mysteriously disappears, "
					+ "the fish drops a scroll on the ground. "
					+ "Maybe you should pick it up, then "
					+ "examine it using the command \"exam "
					+ "<item name>\".");
		}

		@Override
		protected void doFail() {
			grid.alert("Oh no! What happened?");
		}
		
	};
	
	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public boolean pickUp(Item item) {
		return false;
	}

	@Override
	public boolean dropOff(Item item) {
		tile.drop((Pile)item);
		return true;
	}

	@Override
	public void dropAll() {}

	@Override
	public String talk(int option) {
		nowTalk = nowTalk.pick(option);
		if (nowTalk != null) {
			return nowTalk.toString();
		}
		return "Invalid option.";
	}

	@Override
	public void endTalk() {
		talking = false;
		nowTalk = null;
	}

	@Override
	public String startTalk() {
		talking = true;
		return (nowTalk = talk).toString();
		
	}
	
	@Override
	public boolean isTalking() {
		return talking;
	}

}
