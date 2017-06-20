package textadv.base.plot;

import java.io.Serializable;

import textadv.base.outfits.Describable;
import textadv.base.world.Grid;

public abstract class Quest implements Describable, Serializable {
	
	private static final long serialVersionUID = 631059467768092448L;
	
	private Status status = Status.NOT_STARTED;
	private String name;
	
	public Quest(String name, Grid grid) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getInfo() {
		return status.toString();
	}
	
	@Override
	public String toString(){
		return describe();
	}
	
	public Status getStatus() {
		return status;
	}

	protected abstract void doStart();
	
	public final boolean start() {
		if (status == Status.NOT_STARTED) {
			status = Status.STARTED;
			doStart();
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract void doFinish();
	
	public final boolean finish() {
		if (status == Status.STARTED) {
			status = Status.FINISHED;
			doFinish();
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract void doFail();
	
	public final boolean fail() {
		if (status == Status.FAILED) {
			status = Status.FAILED;
			doFail();
			return true;
		} else {
			return false;
		}
	}
	
	public void reset() {
		status = Status.NOT_STARTED;
	}
	
	public static enum Status implements Describable {
		
		NOT_STARTED("not started", "This quest has not been started."),
		STARTED("started", "This quest is in the process of being completed."),
		FINISHED("finished", "You have finished this quest."),
		FAILED("failed", "You have failed in completing this quest.");
		
		private String name;
		private String info;
		
		Status(String name, String info) {
			this.name = name;
			this.info = info;
		}
		
		@Override
		public String toString() {
			return describe();
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getInfo() {
			return info;
		}
		
	}
	
}
