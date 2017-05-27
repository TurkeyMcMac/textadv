package textadv.main;

import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.Goblin;
import textadv.base.population.Stick;
import textadv.base.population.Wall;
import textadv.base.world.*;

public final class Main {
	
	static Grid world = new Grid(100, 100, Tile.empty());
	
	static int frame = 0;
	
	private static void screen(String[] args) {
		world.getPlayer().getController().takeOrder(args);
		world.loadPlayer(20, 10);
		world.update();
		System.out.println("\033c" + world.window() + "\n\nplayer>");
	}
	
	public static void main(String[] args) {
		Player player = new Player(CarDir.NORTH, null);
		world.drop(player, 30, 30);
		world.drop(new Stick(null), 31, 30);
		world.drop(new Stick(null), 31, 30);
		world.drop(new Goblin(CarDir.NORTH, null), 25, 23);
		world.place(new Wall("Wall", "A solid wall.", '#', null), 30, 29);
		world.setPlayer(player);
		screen(new String[] {"help"});
		while (true) { 
			screen(System.console().readLine().trim().split("\\s+"));
		}
	}

}
