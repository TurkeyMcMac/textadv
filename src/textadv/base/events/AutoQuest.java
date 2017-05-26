package textadv.base.events;

public class AutoQuest implements Quest {
	
	private String name;
	private String info;
	private Status status;
	private Event start;
	private Event end;
	private Event fail;
	
	public AutoQuest(String name, String info, Event start, Event end, Event fail) {
		this.name = name;
		this.info = info;
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
			status = Status.ACTIVE;
			return start.isMet();
		}
		return false;
	}

	@Override
	public boolean end() {
		if (status == Status.ACTIVE) {
			status = Status.FINISHED;
			return end.isMet();
		}
		return false;
	}

	@Override
	public boolean fail() {
		if (status == Status.ACTIVE) {
			status = Status.FAILED;
			return fail.isMet();
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
	
	
	public Status checkStatus() {
		if (!	 start()) {
			if (!end()) {
				 fail();
			}
		}
		return status;
	}
	
}
