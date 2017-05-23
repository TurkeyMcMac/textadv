package textadv.base.events;

import java.util.function.Consumer;
import java.util.function.Predicate;

import textadv.base.world.Grid;

public class Event extends Condition {
	
	private Consumer<Grid> event;
	
	public Event(Grid grid, Predicate<Grid> condition, Consumer<Grid> event) {
		super(grid, condition);
		this.event = event;
	}
	
	public void update() {
		if (isMet()) {
			event.accept(grid);
		}
	}
	
}