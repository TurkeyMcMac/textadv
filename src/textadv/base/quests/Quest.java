package textadv.base.quests;

import java.util.function.Consumer;

import textadv.base.outfits.Describable;
import textadv.base.world.Grid;

public class Quest implements Describable {
	
	public enum Status {
		FINISHED, FAILED, ACTIVE, INACTIVE
	}
	
	private String name;
	private String info;
	private Grid grid;
	private Status status;
	private Consumer<Grid> start;
	private Consumer<Grid> end;
	private Consumer<Grid> fail;
	
	public Quest(String name,
				 String info,
				 Grid grid,
				 Consumer<Grid> start,
				 Consumer<Grid> end,
				 Consumer<Grid> fail
	) {
		this.name = name;
		this.info = info;
		this.grid = grid;
		this.start = start;
		this.end = end;
		this.fail = fail;
		this.status = Status.INACTIVE;
	}

	public String getName() {
		return name;
	}

	public String getInfo() {
		return info;
	}

	public boolean start() {
		if (status == Status.INACTIVE) {
			start.accept(grid);
			status = Status.ACTIVE;
			return true;
		}
		return false;
	}

	public boolean end() {
		if (status == Status.ACTIVE) {
			end.accept(grid);
			status = Status.FINISHED;
			return true;
		}
		return false;
	}

	public boolean fail() {
		if (status == Status.ACTIVE) {
			fail.accept(grid);
			status = Status.FAILED;
			return true;
		}
		return false;
	}
	
	public void reset() {
		status = Status.INACTIVE;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
	
}
