package textadv.base.plot;

import java.util.function.Predicate;

import textadv.base.world.Grid;

public class Event {
	
	protected Grid grid;
	private Predicate<Grid> condition;
	
	public Event(Grid grid, Predicate<Grid> condition) {
		this.grid = grid;
		this.condition = condition;
	}
	
	public boolean isMet() {
		return condition.test(grid);
	}
	
}
