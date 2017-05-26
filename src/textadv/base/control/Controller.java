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
	protected <U extends Being> boolean doOrder(BiConsumer<U, T> order) {
		if (suppressed || order == null) {
			return false;
		} else {
			order.accept((U)controlled, (T)this);
			return true;
		}
	}
	
	public abstract void control();
	
}
