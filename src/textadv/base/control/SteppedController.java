package textadv.base.control;

import java.util.function.BiConsumer;
import java.util.Map;

import textadv.base.world.Being;

public final class SteppedController extends AiController {
	
	private int tick = -1;
	private int sleep = 0;
	private BiConsumer<Being, SteppedController>[] steps;
	private Map<String, Object> vars;
	
	@SafeVarargs
	public SteppedController(BiConsumer<Being, SteppedController> ... steps) {
		this.steps = steps;
	}
	
	public void GOTO(int line) {
		tick = line - 1;
	}
	
	public void WAIT(int time) {
		sleep = time;
	}
	
	public void VAR(String var, Object val) {
		vars.put(var, val);
	}
	
	public boolean SET(String var, Object val) {
		if (vars.containsKey(var)) {
			vars.put(var, val);
			return true;
		}
		return false;
	}
	
	public Object VAL(String var) {
		return vars.get(var);
	}
	
	public boolean END(String var) {
		return vars.remove(var) != null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected BiConsumer<Being, SteppedController> getOrder() {
		if (sleep > 0) {
			sleep--;
			return null;
		} else {
			tick++;
			return steps[tick];
		}
		
	}

}
