package textadv.base.player;

import java.util.function.BiConsumer;

import textadv.base.control.AIOrder;
import textadv.base.control.Controller;
import textadv.base.world.*;

public final class PlayerController implements Controller<PlayerController> {
	
	private static final long serialVersionUID = -1938356098085148202L;
	
	private boolean suppressed = false;
	
	public PlayerController() {}
	
	public void setOrder(BiConsumer<Player, PlayerController> order) {}
	
	@Override
	public void control() {}

	@Override
	public Being getControlled() {
		return null;
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
	public void setOrder(AIOrder<PlayerController> order) {}

	@Override
	public AIOrder<PlayerController> getOrder() {
		return null;
	}

	@Override
	public void setControlled(Being controlled) {}
	
}
