package textadv.base.player;

import java.util.function.Function;

import jwmh.misc.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import textadv.base.control.Controller;
import textadv.base.directions.*;
import textadv.base.world.*;
import textadv.base.outfits.*;

public final class PlayerController extends Controller<PlayerController> {
	
	private BiConsumer<Player, PlayerController> order;
	
	private static CarDir parseCarDir(String order) {
		switch (order.toLowerCase()) {
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
	
	private static Describable findByName(String name, List<? extends Describable> list) {
		for (Describable i : list) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	private static final String NO_ORDER = "Unknown order.";
	
	private static final String NO_ITEM = "No such item.";
	
	private static final String NO_TOOL = "No such tool.";
	
	
	/*
	 * Structure:
	 *   Key:
	 *     Name
	 *     Argument number
	 *   Value:
	 *     Order
	 *     Description:
	 *       Argument info
	 *       Explanation
	 * */
	private static final transient Map<
		Pair<String, Integer>, 
		Pair<
			Function<
				String[], 
				BiConsumer<Player, PlayerController>>,
			Pair<String[], String>>> ORDERS = new HashMap<>();
	
	static {
		ORDERS.put(new Pair<>("step", 1), new Pair<>((String[] args) -> {
			
			CarDir d = parseCarDir(args[1].toLowerCase());
			return d == null ?
				(b, c) -> {}:
				(b, c) -> {
					b.turn(d);
					b.log(b.step() ?
							"You step " + d.toString().toLowerCase() + '.' :
							"You cannot step there.");
				};
				
		}, new Pair<>(new String[] {"cardinal direction"}, "Step in a direction.")));
		ORDERS.put(new Pair<>("look", 1), new Pair<>((String[] args) -> {
			
			CarDir d = parseCarDir(args[1].toLowerCase());
			return d == null ?
				(b, c) -> {}:
				(b, c) -> b.log(b.look(d));
				
		}, new Pair<>(new String[] {"cardinal direction"}, "Look at a tile in one's vicinity.")));
		ORDERS.put(new Pair<>("exam", 1), new Pair<>((String[] args) -> {
			
			return (b, c) -> {
				Describable found = findByName(args[1], b.getInventory());
				b.log(found == null ?
						NO_ITEM :
						found);
			};
			
		}, new Pair<>(new String[] {"item name"}, "Examine an item in one's inventory.")));
		ORDERS.put(new Pair<>("inv", 0), new Pair<>((String[] args) -> {
			
			return (b, c) -> {
				b.log(Describable.nameList("Inventory:", b.getInventory()));
			};
			
		}, new Pair<>(new String[] {}, "List the contents of one's inventory.")));
		ORDERS.put(new Pair<>("help", 0), new Pair<>((String[] args) -> {
			
			return (b, c) -> {
				String stringified = "List of orders:";
				for (Pair<String, Integer> p : ORDERS.keySet()) {
					stringified += "\n\t" + p.start + ' ';
					Pair<String[], String> description = ORDERS.get(p).end;
					for (String a : description.start) {
						stringified += '<' + a + "> ";
					}
					stringified += ' ' + description.end + '\n';
				}
				b.log(stringified);
			};
			
		}, new Pair<>(new String[] {}, "List all possible orders")));
		ORDERS.put(new Pair<>("pick", 1), new Pair<>((String[] args) -> {
			
			return (b, c) -> {
				Tile tile = b.getTile();
				Grid grid = tile.getGrid();
				int x = tile.getX();
				int y = tile.getY();
				Tile[] searchList = new Tile[] {
						grid.getTile(x  , y  ),
						grid.getTile(x, y - 1),
						grid.getTile(x + 1, y),
						grid.getTile(x, y + 1),
						grid.getTile(x - 1, y)
				};
				for (Tile t : searchList) {
					Describable found = findByName(args[1], t.getPiles());
					if (found instanceof Item) {
						b.pickUp((Item)found);
						b.log("You pick up " + args[1] + '.');
						return;
					}
				}
				b.log(NO_ITEM);
			};
			
		}, new Pair<>(new String[] {"item name"}, "Pick up an item from one's vicinity.")));
		ORDERS.put(new Pair<>("drop", 1), new Pair<>((String[] args) -> {
			
			return (b, c) -> {
				Item found = (Item)findByName(args[1], b.getInventory());
				if (found != null) {
					b.dropOff(found);
					b.log("You drop " + args[1] + '.');
				} else {
					b.log(NO_ITEM);
				}
			};
			
		}, new Pair<>(new String[] {"item name"}, "Drop an item from one's inventory.")));
		ORDERS.put(new Pair<>("drop", 2), new Pair<>((String[] args) -> {
			
			return (b, c) -> {
				int times = 0;
				try {
					times = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					b.log(NO_ORDER);
					return;
				}
				int number = 0;
				for (int i = 0; i < times; i++) {
					Item found = (Item)findByName(args[1], b.getInventory());
					if (found != null) {
						b.dropOff(found);
						number++;
					}
				}
				b.log("You drop " + number + " of " + args[1] + '.');
			};
			
		}, new Pair<>(new String[] {"item name", "number"}, "Drop a number of a certain type of item from one's inventory.")));
		ORDERS.put(new Pair<>("wld", 1), new Pair<>((String[] args) -> {
			
			return (b, c) -> {
				Describable found = findByName(args[1], b.getInventory());
				if (found instanceof Tool<?>) {
					b.log(b.wield((Tool<?>)found) ?
							"You wield " + args[1] + '.' :
							NO_TOOL);
					return;
				}
				b.log(NO_TOOL);
			};
			
		}, new Pair<>(new String[] {"weapon name"}, "Wield a weapon from one's inventory.")));
		ORDERS.put(new Pair<>("unwld", 1), new Pair<>((String[] args) -> {
			
			return (b, c) -> {
				Describable found = findByName(args[1], b.getWielded());
				if (found != null) {
					b.unwield((Tool<?>)found);
					b.log("You unwield " + args[1] + '.');
					return;
				}
				b.log(NO_TOOL);
			};
			
		}, new Pair<>(new String[] {"weapon name"}, "Unwield a weapon from one's inventory.")));
		ORDERS.put(new Pair<>("atk", 2), new Pair<>((String[] args) -> {
			
			return (b, c) -> {
				Tile target = b.look(parseCarDir(args[1]));
				for (Pile p : target.getPiles()) {
					if (p instanceof Healthy) {
						if (b.getAttack(args[2]) != null) {
							b.attack((Healthy)p, args[2]);
							b.log("You attack " + p.getName() + '.');
							return;
						} else {
							b.log("No such attack.");
							return;
						}
					}
				}
				b.log("No such attackable being.");
			};
			
		}, new Pair<>(new String[] {"cardinal direction", "attack name"}, "Attack in some direction with a specified attack.")));
	}
	
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
		Function<String[], BiConsumer<Player, PlayerController>> interpretation = ORDERS.get(new Pair<>(givenOrder[0], givenOrder.length - 1)).start;
		if (interpretation == null) {
			order = (b, c) -> b.log(NO_ORDER);
		} else {
			order = interpretation.apply(givenOrder);
		}
	}
	
	public void setOrder(BiConsumer<Player, PlayerController> order) {
		this.order = order;
	}
	
	@Override
	public void control() {
		doOrder(order);
	}
	
}
