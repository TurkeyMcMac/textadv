package textadv.base.control;

import java.io.Serializable;

import textadv.base.world.Being;

public interface Controller<T extends Controller<T>> extends Serializable {
	
	public void setControlled(Being controlled);
	
	public Being getControlled();
	
	public void suppress();
	
	public void unsuppress();
	
	public boolean isSuppressed();
	
	public void setOrder(AIOrder<T> order);
	
	public AIOrder<T> getOrder();
	
	@SuppressWarnings("unchecked")
	default boolean doOrder() {
		AIOrder<T> order = getOrder();
		if (isSuppressed() || order == null) {
			return false;
		} else {
			order.accept((T)this);
			return true;
		}
	}
	
	public abstract void control();
	
}
