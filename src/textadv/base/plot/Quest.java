package textadv.base.plot;

import java.io.Serializable;

import textadv.base.outfits.Describable;

public abstract class Quest implements Describable, Serializable {
	
	private static final long serialVersionUID = 631059467768092448L;
	
	private Status status = Status.NOT_STARTED;
	private String name;
	
	public Quest(String name) {
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
			doStart();
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract void doFinish();
	
	public final boolean finish() {
		if (status == Status.STARTED) {
			doFinish();
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract void doFail();
	
	public final boolean fail() {
		if (status == Status.FAILED) {
			doFail();
			return true;
		} else {
			return false;
		}
	}
	
	public void reset() {
		status = Status.NOT_STARTED;
	}
	
	public static enum Status {
		
		NOT_STARTED("This quest has not been started."),
		STARTED("This quest is in the process of being completed."),
		FINISHED("This quest has been finished."),
		FAILED("You have failed in completing this quest.");
		
		private String message;
		
		Status(String message) {
			this.message = message;
		}
		
		@Override
		public String toString() {
			return message;
		}
		
	}
	
}
