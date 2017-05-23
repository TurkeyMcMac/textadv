package textadv.base.control;

import java.util.function.BiConsumer;

import textadv.base.world.Being;

public final class ConstantController extends AiController {
	
	private BiConsumer<Being, ConstantController> order;
	
	public ConstantController(BiConsumer<Being, ConstantController> order) {
		this.order = order;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected <T extends AiController> BiConsumer<Being, T> getOrder() {
		return (BiConsumer<Being, T>)order;
	}

}
