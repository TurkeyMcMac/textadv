package textadv.base.resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import jwmh.commands.Command;
import jwmh.commands.CommandSet;
import jwmh.dcn.*;
import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.Goblin;
import textadv.base.population.Stick;
import textadv.base.world.Grid;
import textadv.base.world.Pile;

public class Grids {
	
	private Grid grid;
	
	public Grids(Grid grid) {
		this.grid = grid;
	}
	
	@SuppressWarnings("serial")
	private Map<String, Supplier<Pile>> pileTable = new HashMap<String, Supplier<Pile>>() {{
		put("player", () -> {
			Player player = new Player(CarDir.NORTH);
			grid.setPlayer(player);
			return player;
		});
		put("goblin", () -> new Goblin(CarDir.NORTH));
		put("stick", () -> new Stick());
	}};
	
	private CommandSet<Void> buildCmds = new CommandSet<>(
		new Command<Void>("drop")
			.setArgNames("name", "x", "y")
			.setEffect((String[] args) -> {
				Pile pile = pileTable.get(args[1]).get();
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
