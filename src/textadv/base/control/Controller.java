package textadv.base.control;

import java.util.function.*;

import textadv.base.world.Being;

public abstract class Controller<T extends Controller<T>> {

	protected Being controlled;
	private boolean suppressed;
	
	public void setControlled(Being controlled) {
		this.controlled = controlled;
	}
	
	public Being getControlled() {
		return controlled;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void unsuppress() {
		suppressed = false;
	}
	
	@SuppressWarnings("unchecked")
	protected boolean giveOrder(BiConsumer<Being, T> order) {
		if (suppressed) {
			return false;
		} else {
			order.accept(controlled, (T)this);
			return true;
		}
	}
	
	public abstract void control();
	
}
