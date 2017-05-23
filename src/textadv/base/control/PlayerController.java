package textadv.base.control;

import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import textadv.base.directions.*;
import textadv.base.player.Player;
import textadv.base.world.Being;

public final class PlayerController extends Controller<PlayerController> {
	
	private BiConsumer<Player, PlayerController> order;
	
	@SuppressWarnings("serial")
	private static final Map<String, Function<String[], BiConsumer<Player, PlayerController>>> ORDERS = new HashMap<String, Function<String[], BiConsumer<Player, PlayerController>>>() {{
		put("step", (String[] args) -> {
			CarDir d;
			switch (args[1].toLowerCase()) {
				case "n":
				case "north":
					d = CarDir.NORTH;
					break;
				case "e":
				case "east":
					d = CarDir.EAST;
					break;
				case "s":
				case "south":
					d = CarDir.SOUTH;
					break;
				case "w":
				case "west":
					d = CarDir.WEST;
					break;
				default:
					return (b, c) -> {};
			}
			return (b, c) -> { b.step(d); };
		});
	}};
	
	public PlayerController() {}
	
	@Override
	@Deprecated
	public void setControlled(Being controlled) {
		this.controlled = controlled;
	}
	
	public void setControlled(Player controlled) {
		this.controlled = controlled;
	}
	
	public void takeOrder(String[] givenOrder) {
		Function<String[], BiConsumer<Player, PlayerController>> interpretation = ORDERS.get(givenOrder[0]);
		if (interpretation != null) {
			order = interpretation.apply(givenOrder);
		}
	}
	
	@Override
	public void control() {
		if (order != null) {
			order.accept((Player) controlled, this);
		}
	}
	
}
