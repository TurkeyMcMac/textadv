package textadv.base.player;

import java.util.function.Function;

import jwmh.misc.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import textadv.base.control.Controller;
import textadv.base.directions.*;
import textadv.base.world.Being;
import textadv.base.outfits.Item;

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
	private static final Map<Pair<String, Integer>, Function<String[], BiConsumer<Player, PlayerController>>> ORDERS = new HashMap<Pair<String, Integer>, Function<String[], BiConsumer<Player, PlayerController>>>() {{
		put(new Pair<>("step", 1), (String[] args) -> {
			CarDir d = parseCarDir(args[1].toLowerCase());
			return d == null ?
				(b, c) -> {}:
				(b, c) -> b.step(d);
		});
		put(new Pair<>("look", 1), (String[] args) -> {
			CarDir d = parseCarDir(args[1].toLowerCase());
			return d == null ?
				(b, c) -> {}:
				(b, c) -> b.log(b.look(d));
		});
		put(new Pair<>("exam", 1), (String[] args) -> {
			
			return (b, c) -> {
				for (Item i : b.getInventory()) {
					if (i.getName() == args[1]) {
						b.log(i.describe());
					}
				}
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
		Function<String[], BiConsumer<Player, PlayerController>> interpretation = ORDERS.get(new Pair<>(givenOrder[0], givenOrder.length - 1));
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
