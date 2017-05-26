package textadv.base.player;

import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import textadv.base.control.Controller;
import textadv.base.directions.*;
import textadv.base.world.Being;

public final class PlayerController extends Controller<PlayerController> {
	
	private BiConsumer<Player, PlayerController> order;
	
	private static CarDir parseCarDir(String order) {
		switch (order) {
		case "n":
		case "north":
			return CarDir.NORTH;
		case "e":
		case "east":
			return CarDir.EAST;
		case "s":
		case "south":
			return CarDir.SOUTH;
		case "w":
		case "west":
			return CarDir.WEST;
		default:
			return null;
		}
	}
	
	@SuppressWarnings("serial")
	private static final Map<String, Function<String[], BiConsumer<Player, PlayerController>>> ORDERS = new HashMap<String, Function<String[], BiConsumer<Player, PlayerController>>>() {{
		put("step", (String[] args) -> {
			CarDir d = parseCarDir(args[1].toLowerCase());
			return d == null ?
				(b, c) -> {}:
				(b, c) -> { b.step(d); };
		});
		put("look", (String[] args) -> {
			CarDir d = parseCarDir(args[1].toLowerCase());
			return d == null ?
				(b, c) -> {}:
				(b, c) -> {
					b.log(b.look(d).getGround());
				};
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
