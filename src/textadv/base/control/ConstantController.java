package textadv.base.control;

import java.util.function.Consumer;

import textadv.base.world.Being;

public final class ConstantController implements Controller<ConstantController> {
	
	private static final long serialVersionUID = 2112439959752803309L;
	
	private Being controlled;
	private Consumer<ConstantController> order;
	private boolean suppressed = false;
	
	public ConstantController(Consumer<ConstantController> order) {
		this.order = order;
	}
	
	@Override
	public void setControlled(Being controlled) {
		this.controlled = controlled;
	}

	@Override
	public Being getControlled() {
		return controlled;
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

	@Override
	public void unsuppress() {
		suppressed = false;
	}

	@Override
	public boolean isSuppressed() {
		return suppressed;
	}

	@Override
	public void setOrder(Consumer<ConstantController> order) {
		this.order = order;
	}

	@Override
	public Consumer<ConstantController> getOrder() {
		return order;
	}

	@Override
	public void control() {
		doOrder();
	}

}
