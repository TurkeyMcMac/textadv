package textadv.base.control;

import java.util.function.BiConsumer;

import textadv.base.world.Being;

public abstract class AiController extends Controller<AiController> {
	
	protected abstract <T extends AiController> BiConsumer<Being, T> getOrder();
	
	@Override
	public void control() {
		BiConsumer<Being, AiController> order = getOrder();
		if (order != null) {
			doOrder(order);
		}
	}

}
