package textadv.base.control;

import java.util.function.*;

import textadv.base.world.Being;

public interface Controller<T extends Controller<T>> {
	
	public void setControlled(Being controlled);
	
	public Being getControlled();
	
	public void suppress();
	
	public void unsuppress();
	
	public boolean isSuppressed();
	
	public void setOrder(Consumer<T> order);
	
	public Consumer<T> getOrder();
	
	@SuppressWarnings("unchecked")
	default boolean doOrder() {
		Consumer<T> order = getOrder();
		if (isSuppressed() || order == null) {
			return false;
		} else {
			order.accept((T)this);
			return true;
		}
	}
	
	public abstract void control();
	
}
