package textadv.main;

import jwmh.misc.*;

import textadv.base.directions.CarDir;
import textadv.base.player.Player;
import textadv.base.population.Stick;
import textadv.base.population.Wall;
import textadv.base.world.*;

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
		Wall wall = new Wall("Wall", "A solid wall.", '#', null);
		Stick stick = new Stick(null);
		Stick st1ck = new Stick(null);
		world.drop(player, 3, 3);
		world.drop(stick, 3, 3);
		world.drop(st1ck, 3, 3);
		player.pickUp(stick);
		player.pickUp(st1ck);
		world.place(wall, 3, 2);
		world.setPlayer(player);
		player.getController().takeOrder(new String[] {"examine"});
		world.loadPlayer(3, 3);
		world.update();
		System.out.println(world.window());
	}

}
