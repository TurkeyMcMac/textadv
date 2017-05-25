package textadv.base.events;

import textadv.base.descriptions.Describable;

public class Quest implements Describable {
	
	private String name;
	private String info;
	private Event start;
	private Event finish;
	private Event failure;
	private Status status;
	
	public Quest(String name, String info, Event start, Event finish, Event failure) {
		this.name = name;
		this.info = info;
		this.start = start;
		this.finish = finish;
		this.failure = failure;
		status = Status.NOT_ACTIVE;
	}
	
	public enum Status {
		FINISHED, FAILED, ACTIVE, NOT_ACTIVE
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getInfo() {
		return info;
	}
	
	public void start() {
		status = Status.ACTIVE;
	}
	
	public void reset() {
		status = Status.NOT_ACTIVE;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public Status checkStatus() {
		switch (status) {
			case NOT_ACTIVE:
				if (start.isMet()) {
					status = Status.ACTIVE;
				}
				break;
			case ACTIVE:
				if (finish.isMet()) {
					status = Status.FINISHED;
				} else if (failure.isMet()) {
					status = Status.FAILED;
				}
				break;
			default:
				break;
		}
		return status;
	}
	
}
