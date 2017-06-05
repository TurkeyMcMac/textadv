package textadv.base.player;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
	public void setOrder(Consumer<PlayerController> order) {}

	@Override
	public Consumer<PlayerController> getOrder() {
		return null;
	}

	@Override
	public void setControlled(Being controlled) {}
	
}
