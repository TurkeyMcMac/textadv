package textadv.main;

import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.*;
import textadv.base.world.*;
import textadv.ui.PlayingUserInterface;
import textadv.ui.BasicUserInterface;

public final class Main {
	
	public static Grid world;
	
	public static PlayingUserInterface playerUI;
	
	private static void screen(String input) {
		System.out.println("\033c" + playerUI.tryRunFrom(input) + "\n>>");
	}
	
	public static void buildWorld() {
		Player player = new Player(CarDir.NORTH);
		world.drop(player, 10, 10);
		world.setPlayer(player);
		world.drop(new Goblin(CarDir.NORTH), 6, 6);
		world.drop(new Hat(), 7, 8);
		world.drop(new Hat(), 9, 8);
		world.drop(new Stick(), 10, 9);
		world.drop(new Stick(), 9, 10);
	}
	
	public static void main(String[] args) {
		new BasicUserInterface().run(args);
		screen("welcome");
		while(true) {
			screen(System.console().readLine());
		}
	}

}
