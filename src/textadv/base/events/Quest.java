package textadv.base.events;

import textadv.base.outfits.Describable;

interface Quest extends Describable {
	
	public enum Status {
		FINISHED, FAILED, ACTIVE, INACTIVE
	}
	
	public boolean start();
	
	public boolean end();
	
	public boolean fail();
	
	public void reset();
	
	public void setStatus(Status status);
	
	public Status getStatus();
	
}
