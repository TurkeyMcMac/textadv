package textadv.main;

import java.io.Console;

import jwmh.misc.*;

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
		Wall wall = new Wall("Wall", "A solid wall.", '#', null);
		Goblin gobbo = new Goblin(CarDir.NORTH, null);
		Stick stick = new Stick(null);
		Stick st1ck = new Stick(null);
		world.drop(player, 30, 30);
		world.drop(stick, 30, 30);
		world.drop(st1ck, 30, 30);
		world.drop(gobbo, 25, 23);
		player.pickUp(stick);
		player.pickUp(st1ck);
		world.place(wall, 30, 29);
		world.setPlayer(player);
		screen(new String[] {"help"});
		while (true) { 
			screen(System.console().readLine().split("\\s+"));
		}
	}

}
