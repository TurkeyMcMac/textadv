package textadv.base.plot;

import java.io.Serializable;
import java.util.function.Predicate;

import textadv.base.world.Grid;

public class Condition implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected Grid grid;
	private Predicate<Grid> condition;
	
	public Condition(Grid grid, Predicate<Grid> condition) {
		this.grid = grid;
		this.condition = condition;
	}
	
	public boolean isMet() {
		return condition.test(grid);
	}
	
}
