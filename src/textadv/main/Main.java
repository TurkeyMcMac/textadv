package textadv.main;

import java.io.*;

import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.*;
import textadv.base.world.*;
import textadv.ui.UserInterface;

public final class Main {
	
	private static Grid world;
	
	private static String location;
	
	private static UserInterface ui;
	
	private static void screen(String input) {
		System.out.println("\033c" + ui.read(input) + "\n>>");
	}
	
	private static void badArgs() {
		System.out.println("Invalid arguments.");
		System.exit(666);
	}
	
	private static void buildWorld() {
		Player player = new Player(CarDir.NORTH);
		world.drop(player, 10, 10);
		world.setPlayer(player);
		world.drop(new Goblin(CarDir.NORTH), 6, 6);
	}
	
	private static final String SAVES = "src/saves/";

	public static void save() {
		try {
			ObjectOutputStream saveData = new ObjectOutputStream(
											new FileOutputStream(
												new File(location)));
			saveData.writeObject(world);
			saveData.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Grid load(String name) {
		String location = SAVES + name;
		try {
			ObjectInputStream saveData = new ObjectInputStream(
											new FileInputStream(
												new File(location)));
			Grid world;
			world = (Grid)saveData.readObject();
			saveData.close();
			return world;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		if (args.length >= 2) {
			switch(args[0]) {
				case "-n":
				case "--new":
					world = new Grid(100, 100, Tile.empty());
					buildWorld();
					ui = new UserInterface(world);
					location = SAVES + args[1];
					break;
				case "-l":
				case "--load":
					world = load(args[1]);
					ui = new UserInterface(world);
					location = SAVES + args[1];
					break;
				default:
					badArgs();
			}
		} else {
			badArgs();
		}
		screen("welcome");
		while(true) {
			screen(System.console().readLine());
		}
	}

}
