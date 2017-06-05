package textadv.base.control;

import textadv.base.world.Being;

public final class ConstantController implements Controller<ConstantController> {
	
	private static final long serialVersionUID = 2112439959752803309L;
	
	private Being controlled;
	private AIOrder<ConstantController> order;
	private boolean suppressed = false;
	
	public ConstantController(AIOrder<ConstantController> order) {
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
	public void setOrder(AIOrder<ConstantController> order) {
		this.order = order;
	}

	@Override
	public AIOrder<ConstantController> getOrder() {
		return order;
	}

	@Override
	public void control() {
		doOrder();
	}

}
