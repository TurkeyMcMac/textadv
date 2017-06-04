package textadv.main;

import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.*;
import textadv.base.world.*;
import textadv.ui.UserInterface;

public final class Main {
	
	private static Grid world = new Grid(100, 100, Tile.empty());
	
	private static void buildWorld() {
		Player player = new Player(CarDir.NORTH);
		world.drop(player, 30, 30);
		world.setPlayer(player);
		world.drop(new Stick(), 31, 30);
		world.drop(new Stick(), 31, 30);
		world.drop(new Hat(), 29, 30);
		world.drop(new Block(), 29, 28);
		world.drop(new Goblin(CarDir.NORTH), 25, 23);
		world.place(new Wall("Wall", "A solid wall.", '#'), 30, 29);
	}
	
	private static UserInterface ui = new UserInterface(world);
	
	private static void screen(String input) {
		System.out.println("\033c" + ui.read(input) + "\n>>");
	}
	
	public static void main(String[] args) {
		buildWorld();
		screen("welcome");
		while(true) { 
			screen(System.console().readLine());
		}
	}

}
