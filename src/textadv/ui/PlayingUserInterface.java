package textadv.ui;

import textadv.base.directions.CarDir;
import textadv.base.directions.RelDir;
import textadv.base.outfits.Armor;
import textadv.base.outfits.Describable;
import textadv.base.outfits.Healthy;
import textadv.base.outfits.Item;
import textadv.base.outfits.Talker;
import textadv.base.outfits.Tool;
import textadv.base.player.Player;
import textadv.base.player.PlayerController;
import textadv.base.world.Grid;
import textadv.base.world.Pile;
import textadv.base.world.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jwmh.commands.*;

public final class PlayingUserInterface extends UserInterface<String> {
	
	private static int loadX = 10,
					   loadY = 5;
	
	Grid grid;
	Player player;
	PlayerController controller;
	
	public PlayingUserInterface(Grid grid) {
		super(null);
		this.grid = grid;
		player = grid.getPlayer();
		controller = player.getController();
		commands = new CommandSet<String>(
			new Command<String>("welcome")
				.setArgNames()
				.setInfo("A welcome message.")
				.setEffect((String[] args) -> "Welcome to textadv!\n\n"
											+ commands.getString("help", 0) + "\n\n"
											+ commands.getString("help", 2)),
			new Command<String>("help")
				.setArgNames()
				.setInfo("List all commands.")
				.setEffect((String[] args) -> commands.toString()),
			new Command<String>("help")
				.setArgNames("command name", "number of arguments")
				.setInfo("Describe one command.")
				.setEffect((String[] args) -> {
					int argNum;
					try {
						argNum = Integer.parseInt(args[2]);
					} catch (NumberFormatException e) {
						return "Requires a number of arguments";
					}
					return commands.getString(args[1], argNum);
				}),
			new InGameCommand<String>("step", controller)
				.setArgNames("cardinal direction")
				.setInfo("Step in a direction.")
				.setEffect((String[] args) -> {
					CarDir direction = parseCarDir(args[1]);
					if (direction == null) {
						return noDir;
					}
					player.turn(direction);
					boolean stepped = player.step();
					grid.loadPlayer(loadX, loadY);
					String drawn = grid.draw();
					if (stepped) {
						grid.update();
						return drawn + "\nYou step " + direction.toString().toLowerCase() + '.';
					} else {
						return drawn + "\nYou cannot step there.";
					}
				}),
			new Command<String>("map")
				.setArgNames()
				.setInfo("Map one's surroundings.")
				.setEffect((String[] args) -> {
					grid.loadPlayer(loadX, loadY);
					return grid.draw();
				}),
			new Command<String>("look")
				.setArgNames("cardinal direction")
				.setInfo("Inspect a tile in one's vicinity.")
				.setEffect((String[] args) -> {
					grid.loadPlayer(loadX, loadY);
					return	grid.draw()
						  + '\n'
						  + grid.getPlayer().look(parseCarDir(args[1])).toString();
				}),
			new Command<String>("exam")
				.setArgNames("item name")
				.setInfo("Examine an item in one's inventory")
				.setEffect((String[] args) -> {
					Describable found = findByName(args[1], grid.getPlayer().getInventory());
					return found == null ?
							noItem :
							found.toString();
				}),
			new Command<String>("self")
				.setArgNames()
				.setInfo("Get info about oneself.")
				.setEffect((String[] args) -> 
						Describable.nameList(
							"Inventory:", 
							player.getInventory())
					  + "\n\n"
					  + Describable.nameList(
							"Wearing:",
							player.getArmors())
					  + "\n\n"
					  + Describable.nameList(
							"Wielding:",
							player.getWielded())
					  + "\n\n"
					  + Describable.nameList(
							"Attacks:",
							player.getAttacks().values())
					  + "\n\nWeight: " + player.getWeights() + '/' + player.getMaxWeights()
					  + "\n\nHealth: " + player.getHealth() + '/' + player.getMaxHealth()
						
					),
			new InGameCommand<String>("pick", controller)
				.setArgNames("item name")
				.setInfo("Pick up an item from one's vicinity.")
				.setEffect((String[] args) -> {
					Tile tile = player.getTile();
					int x = tile.getX(),
						y = tile.getY();
					Tile[] searchArray = surroundings(x, y, grid);
					for (Tile t : searchArray) {
						Describable found = findByName(args[1], t.getPiles());
						if (found instanceof Item) {
							grid.loadPlayer(loadX, loadY);
							if (player.pickUp((Item)found)) {
								grid.update();
								return commands.run("self") + "\nYou pick up " + args[1] + '.';
							} else {
								return "That item is too heavy.";
							}
						}
					}
					return noItem;
				}),
			new InGameCommand<String>("drop", controller)
				.setArgNames("item name")
				.setInfo("Drop an item from one's inventory")
				.setEffect((String[] args) -> {
					Item found = (Item)findByName(args[1], player.getInventory());
					grid.loadPlayer(loadX, loadY);
					if (found != null) {
						player.dropOff(found);
						grid.update();
						return commands.run("self") + "\nYou drop " + args[1] + '.';
					} else {
						return noItem;
					}
				}),
			new InGameCommand<String>("drop", controller)
				.setArgNames("item name", "number")
				.setInfo("Drop a number of items from one's inventory.")
				.setEffect((String[] args) -> {
					int times = 0;
					try {
						times = Integer.parseInt(args[2]);
					} catch (NumberFormatException e) {
						return "An integer is required as the second argument.";
					}
					int number = 0;
					for (int i = 0; i < times; i++) {
						Item found = (Item)findByName(args[1], player.getInventory());
						if (found != null) {
							player.dropOff(found);
							number++;
						}
					}
					if (number > 0) {
						grid.loadPlayer(loadX, loadY);
						grid.update();
						return commands.run("self") + "\nYou drop " + number + " of " + args[1] + '.';
					} else {
						return noItem;
					}
				}),
			new InGameCommand<String>("wld", controller)
				.setArgNames("tool name")
				.setInfo("Wield a tool from one's inventory")
				.setEffect((String[] args) -> {
					List<Describable> foundList = findAllByName(args[1], player.getInventory());
					for (Describable found : foundList) {
						if (found instanceof Tool<?>) {
							if (player.wield((Tool<?>)found)) {
								grid.loadPlayer(loadX, loadY);
								grid.update();
								return commands.run("self") + "\nYou wield " + args[1] + '.';
							}
						}
					}
					return noTool;
				}),
			new InGameCommand<String>("unwld", controller)
				.setArgNames("tool name")
				.setInfo("Unwield a tool.")
				.setEffect((String[] args) -> {
					Describable found = findByName(args[1], player.getWielded());
					if (found != null) {
						player.unwield((Tool<?>)found);
						grid.loadPlayer(loadX, loadY);
						grid.update();
						return commands.run("self") + "\nYou unwield " + args[1] + '.';
					}
					return noTool;
				}),
			new InGameCommand<String>("atk", controller)
				.setArgNames("attack name", "cardinal direction")
				.setInfo("Attack in some direction.")
				.setEffect((String[] args) -> {
					Tile target = player.look(parseCarDir(args[2]));
					if (player.getAttack(args[1]) != null) {
						for (Pile p : target.getPiles()) {
							if (p instanceof Healthy) {
								player.attack((Healthy)p, args[1]);
								grid.loadPlayer(loadX, loadY);
								grid.update();
								return commands.run("self") + "\nYou attack " + p.getName() + '.';
							}
						}
						return("No such attackable being.");
					} else {
						return noAtk;
					}
				}),
			new InGameCommand<String>("wear", controller)
				.setArgNames("armor name", "armor direction")
				.setInfo("Wear a bit of armor.")
				.setEffect((String[] args) -> {
					RelDir direction = parseRelDir(args[2]);
					if (direction == null) {
						return noDir;
					}
					List<Describable> foundList = findAllByName(args[1], player.getInventory());
					for (Describable found : foundList) {
						if (found instanceof Armor) {
							if (player.wear((Armor)found, direction)) {
								grid.loadPlayer(loadX, loadY);
								grid.update();
								return commands.run("self") + "\nYou wear " + args[1] + '.';
							}
						}
					}
					return noArmr;
				}),
			new InGameCommand<String>("unwear", controller)
				.setArgNames("armor name")
				.setInfo("Unwear a bit of armor.")
				.setEffect((String[] args) -> {
					Describable found = findByName(args[1], player.getArmors());
					if (found != null) {
						player.unwear((Armor)found);
						grid.loadPlayer(loadX, loadY);
						grid.update();
						return commands.run("self") + "\nYou unwear " + args[1] + '.';
					}
					return noArmr;
				}),
			new InGameCommand<String>("talk", controller)
				.setArgNames("being name")
				.setInfo("Talk to a person in one's vicinity.")
				.setEffect((String[] args) -> {
					Tile tile = player.getTile();
					int x = tile.getX(),
						y = tile.getY();
					Tile[] searchArray = surroundings(x, y, grid);
					for (Tile t : searchArray) {
						Describable found = findByName(args[1], t.getPiles());
						if (found instanceof Talker) {
							return player.listenTo((Talker)found);
						}
					}
					return "No such talking being.";
				}),
			new InGameCommand<String>("resp", controller)
				.setArgNames("option number")
				.setInfo("Respond while conversing.")
				.setEffect((String[] args) -> {
					if (!player.isListening()) {
						return "You are not currently engaged in a conversation.";
					}
					
					int option;
					try {
						option = Integer.parseInt(args[1]);
					} catch (NumberFormatException e) {
						return "An integer argument is required";
					}
					return player.respond(option);
				}),
			new Command<String>("wait")
				.setArgNames()
				.setInfo("Wait one turn.")
				.setEffect((String[] args) -> {
					grid.loadPlayer(loadX, loadY);
					grid.update();
					return grid.draw();
				}),
			new Command<String>("exit")
				.setArgNames()
				.setInfo("Save and exit the game.")
				.setEffect((String[] args) -> {
					BasicUserInterface.save();
					System.exit(0);
					return "";
				}),
			new Command<String>("exit!")
				.setArgNames()
				.setInfo("Exit the game without saving.")
				.setEffect((String[] args) -> {
					System.exit(0);
					return "";
				})
		);
		this.grid = grid;
		this.player = grid.getPlayer();
		this.controller = player.getController();
	}
	
	private static String noDir = "Unknown direction";
	
	private static String noItem = "No such item.";
	
	private static String noTool = "Unwieldable tool.";
	
	private static String noArmr = "Unwearable armor.";
	
	private static String noAtk = "No such attack.";

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
	
	private static RelDir parseRelDir(String order) {
		switch (order.toLowerCase()) {
		case "f":
		case "forward":
			return RelDir.FORWARD;
		case "r":
		case "right":
			return RelDir.RIGHT;
		case "b":
		case "backward":
			return RelDir.BACKWARD;
		case "l":
		case "left":
			return RelDir.LEFT;
		default:
			return null;
		}
	}
	
	private static Describable findByName(String name, Collection<? extends Describable> list) {
		for (Describable i : list) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	private static List<Describable> findAllByName(String name, Collection<? extends Describable> list) {
		List<Describable> foundList = new ArrayList<>();
		for (Describable i : list) {
			if (i.getName().equals(name)) {
				foundList.add(i);
			}
		}
		return foundList;
	}
	
	private static Tile[] surroundings(int x, int y, Grid grid) {
		return new Tile[] {
				grid.getTile(x  , y  ),
				grid.getTile(x, y - 1),
				grid.getTile(x + 1, y),
				grid.getTile(x, y + 1),
				grid.getTile(x - 1, y)
		};
	}
	
	public String read(String givenCmd) {
		try {
			return run(givenCmd);
		} catch (UnknownCommandException e) {
			return "Unknown command.";
		}
	}
	
}
