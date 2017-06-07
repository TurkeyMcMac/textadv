package textadv.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import jwmh.commands.Command;
import jwmh.commands.CommandSet;
import textadv.base.factions.Faction;
import textadv.base.world.Grid;
import textadv.base.world.Tile;
import textadv.main.Main;

public class BasicUserInterface extends UserInterface<Void> {
	
	public static String location;

	public static final String SAVES = "src/saves/";
	
	public static void save() {
		try {
			ObjectOutputStream saveData = new ObjectOutputStream(
											new FileOutputStream(
												new File(location + "world")));
			saveData.writeObject(Main.world);
			saveData.writeObject(Faction.getAll());
			saveData.close();
			Faction.writeAll(location + "factions");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Grid load(String name) {
		location = SAVES + name + '/';
		try {
			ObjectInputStream saveData = new ObjectInputStream(
											new FileInputStream(
												new File(location + "world")));
			Grid world = (Grid)saveData.readObject();
			Faction.setAll((Map<String, Faction>)saveData.readObject());
			Faction.loadAll(location + "factions");
			saveData.close();
			return world;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public BasicUserInterface() {
		super(null);
		commands = new CommandSet<Void>(
				new Command<Void>("-n", "--new")
				.setArgNames("world name")
				.setInfo("Create a new world.")
				.setEffect((String[] args) -> {
					Main.world = new Grid(100, 100, Tile.empty());
					Faction.loadAll("src/resources/factions");
					Main.buildWorld();
					Main.playerUI = new PlayingUserInterface(Main.world);
					location = SAVES + args[1] + '/';
					new File(location).mkdir();
					return null;
				}),
			new Command<Void>("-l", "--load")
				.setArgNames("world name")
				.setInfo("Load a previously created world.")
				.setEffect((String[] args) -> {
					Main.world = load(args[1]);
					Main.playerUI = new PlayingUserInterface(Main.world);
					return null;
				}),
			new Command<Void>("-h", "--help")
				.setArgNames()
				.setInfo("List all commands.")
				.setEffect((String[] args) -> {
					System.out.println(commands);
					System.exit(0);
					return null;
				})
		);
	}

}
