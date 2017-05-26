package textadv.base.events;

import java.util.function.Consumer;

import textadv.base.world.Grid;

public class ManualQuest implements Quest {
	
	private String name;
	private String info;
	private Grid grid;
	private Status status;
	private Consumer<Grid> start;
	private Consumer<Grid> end;
	private Consumer<Grid> fail;
	
	public ManualQuest(String name,
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public boolean start() {
		if (status == Status.INACTIVE) {
			start.accept(grid);
			status = Status.ACTIVE;
			return true;
		}
		return false;
	}

	@Override
	public boolean end() {
		if (status == Status.ACTIVE) {
			end.accept(grid);
			status = Status.FINISHED;
			return true;
		}
		return false;
	}

	@Override
	public boolean fail() {
		if (status == Status.ACTIVE) {
			fail.accept(grid);
			status = Status.FAILED;
			return true;
		}
		return false;
	}
	
	@Override
	public void reset() {
		status = Status.INACTIVE;
	}
	
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public Status getStatus() {
		return status;
	}

}
