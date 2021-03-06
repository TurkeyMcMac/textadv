package textadv.base.control;

import java.util.Map;

import textadv.base.world.Being;

public final class SteppedController implements Controller<SteppedController> {
	
	private static final long serialVersionUID = 2937053840263332981L;
	
	private int tick = -1;
	private int sleep = 0;
	private Being controlled;
	private boolean suppressed = false;
	private AIOrder<SteppedController>[] steps;
	private AIOrder<SteppedController> order;
	private Map<String, Object> vars;
	
	@SafeVarargs
	public SteppedController(AIOrder<SteppedController> ... steps) {
		this.steps = steps;
	}
	
	public void goTo(int line) {
		tick = line - 1;
	}
	
	public void sleep(int time) {
		sleep = time;
	}
	
	public void var(String var, Object val) {
		vars.put(var, val);
	}
	
	public void set(String var, Object val) {
		vars.replace(var, val);
	}
	
	public Object val(String var) {
		return vars.get(var);
	}
	
	public boolean end(String var) {
		return vars.remove(var) != null;
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
		suppressed = true;
	}

	@Override
	public boolean isSuppressed() {
		return suppressed;
	}

	@Override
	public void setOrder(AIOrder<SteppedController> order) {
		this.order = order;
	}

	@Override
	public AIOrder<SteppedController> getOrder() {
		return order;
	}

	@Override
	public void control() {
		if (sleep > 0) {
			sleep--;
		} else {
			tick++;
			if (tick < steps.length) {
				setOrder(steps[tick]);
				doOrder();
			}
		}
		
	}

}
