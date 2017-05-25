package textadv.main;

import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.*;
import textadv.base.world.*;
import textadv.base.talks.*;

public final class Main {
	
	static Grid world = new Grid(20, 10, Tile.empty());
	
	static int frame = 0;
	
	static void frame() {
		world.update();
		frame++;
		System.out.println("frame: " + frame + '\n' + world.draw());
	}
	
	public static void main(String[] args) {
		Player player = new Player(CarDir.NORTH, null);
		world.drop(player, 3, 3);
		world.setPlayer(player);
		Stick stick = new Stick(null);
		Hat hat = new Hat(null);
		Goblin gobbo = new Goblin(CarDir.NORTH, null);
		world.loadPlayer(4, 4);
		System.out.println(world.draw());
	}

}
