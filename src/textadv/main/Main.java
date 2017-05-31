package textadv.main;

import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.*;
import textadv.base.world.*;
import textadv.ui.UserInterface;

public final class Main {
	
	private static Grid world = new Grid(100, 100, Tile.empty()) {{
		Player player = new Player(CarDir.NORTH, null);
		drop(player, 30, 30);
		setPlayer(player);
		drop(new Stick(null), 31, 30);
		drop(new Stick(null), 31, 30);
		drop(new Block(null), 29, 28);
		drop(new Goblin(CarDir.NORTH, null), 25, 23);
		place(new Wall("Wall", "A solid wall.", '#', null), 30, 29);
	}};
	
	private static UserInterface ui = new UserInterface(world);
	
	private static void screen(String input) {
		System.out.println("\033c" + ui.read(input) + "\n>>");
	}
	
	public static void main(String[] args) {
		screen("welcome");
		while (true) { 
			screen(System.console().readLine());
		}
	}

}
