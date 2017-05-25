package textadv.main;

import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.*;
import textadv.base.world.*;
import textadv.base.talks.*;

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
		Stick stick = new Stick(null);
		Hat hat = new Hat(null);
		Goblin gobbo = new Goblin(CarDir.NORTH, null);
		System.out.println(player.listenTo(gobbo, null));
		System.out.println(player.respond(1));
		player.stopListening();
		System.out.println(gobbo.isTalking());
	}

}
