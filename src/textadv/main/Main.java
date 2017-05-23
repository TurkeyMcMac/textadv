package textadv.main;

import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.*;
import textadv.base.world.*;

public final class Main {
	
	static Grid world = new Grid(20, 10, Tile.empty());
	
	static int frame = 0;
	
	static {
		world.setPlayerTile(world.getTile(0, 0));
	}
	
	static void frame() {
		world.update();
		frame++;
		System.out.println("frame: " + frame + '\n' + world.draw());
	}
	
	public static void main(String[] args) {
		Player player = new Player(CarDir.NORTH, null);
		Stick stick = new Stick(10, null);
		Goblin gobbo = new Goblin(CarDir.NORTH, null);
		//player.getController().takeOrder(args);
		world.drop(player, 2, 4);
		world.drop(stick, 2, 4);
		player.pickUp(stick);
		player.wield(stick);
		world.drop(gobbo, 17, 9);
		world.load(0, 0, 19, 9);
		while (gobbo.isAlive()) {
			player.attack(gobbo, "Hit");
			frame();
			System.out.println(gobbo);
		}
	}

}
