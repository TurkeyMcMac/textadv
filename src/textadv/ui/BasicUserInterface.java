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
import textadv.base.resources.Grids;
import textadv.base.world.Grid;
import textadv.base.world.Tile;
import textadv.main.Main;

public class BasicUserInterface extends CommandSet<Void> {
	
	public static String location;

	public static final String SAVES = "src/saves/";
	
	public static void save() {
		System.out.println("Saving world...");
		try {
			ObjectOutputStream saveData = new ObjectOutputStream(
											new FileOutputStream(
												new File(location + "world")));
			System.out.println("Writing world...");
			saveData.writeObject(Main.world);
			saveData.writeObject(Faction.getAll());
			saveData.close();
			System.out.println("Writing factions...");
			Faction.writeAll(location + "factions");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Grid load(String name) {
		System.out.println("Loading save file...");
		location = SAVES + name + '/';
		try {
			ObjectInputStream saveData = new ObjectInputStream(
											new FileInputStream(
												new File(location + "world")));
			System.out.println("Reading world...");
			Grid world = (Grid)saveData.readObject();
			System.out.println("Reading factions...");
			Faction.setAll((Map<String, Faction>)saveData.readObject());
			Faction.loadAll(location + "factions");
			saveData.close();
			return world;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Save file not found.");
			System.exit(86467456);
		}
		return null;
	}
	
	public BasicUserInterface() {
		super();
		addCmds(
				new Command<Void>("-n", "--new")
				.setArgNames("world name")
				.setInfo("Create a new world.")
				.setEffect((String[] args) -> {
					System.out.println("Creating new world...");
					Main.world = new Grid(100, 100, Tile.empty());
					System.out.println("Loading factions...");
					Faction.loadAll("src/resources/factions.dcn");
					System.out.println("Building world...");
					try {
						new Grids(Main.world).buildFrom("src/resources/world.dcn");
					} catch (IOException e) {
						e.printStackTrace();
					};
					Main.playerUI = new PlayingUserInterface(Main.world);
					location = SAVES + args[1] + '/';
					System.out.println("Creating save directory");
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
				.setInfo("List all options.")
				.setEffect((String[] args) -> {
					System.out.println(this);
					System.exit(0);
					return null;
				})
		);
	}

}
