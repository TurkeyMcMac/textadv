package textadv.base.resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import jwmh.commands.Command;
import jwmh.commands.CommandSet;
import jwmh.dcn.*;
import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.Goblin;
import textadv.base.population.LeggedFish;
import textadv.base.population.LoremIpsum;
import textadv.base.population.Stick;
import textadv.base.world.Grid;
import textadv.base.world.Pile;

public class Grids {
	
	private Grid grid;
	
	public Grids(Grid grid) {
		this.grid = grid;
	}
	
	private Map<String, Supplier<Pile>> pileTable = new HashMap<>();
	{
		pileTable.put("player", () -> {
			Player player = new Player(CarDir.NORTH);
			grid.setPlayer(player);
			return player;
		});
		pileTable.put("legged-fish", () -> new LeggedFish(CarDir.NORTH, grid));
		pileTable.put("stick", () -> new Stick());
		pileTable.put("paper-slip", () -> new LoremIpsum());
	}
	
	private CommandSet<Void> buildCmds = new CommandSet<>(
		new Command<Void>("drop")
			.setArgNames("name", "x", "y")
			.setEffect((String[] args) -> {
				Supplier<Pile> pileGiver = pileTable.get(args[1]);
				if (pileGiver == null) {
					return null;
				}
				Pile pile = pileGiver.get();
				if (pile != null) {
					grid.drop(pile, Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				}
				return null;
			})
	);
	
	public void buildFrom(String path) throws IOException {
		CapsuleReader reader = new CapsuleReader(
							       new BufferedReader(
							    	   new FileReader(path)));
		reader.whileReading((o) -> buildCmds.runFrom((String)o));
		reader.close();
	}

}
