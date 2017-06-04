package textadv.ui;

import jwmh.commands.Command;
import textadv.base.player.PlayerController;

public class InGameCommand<U> extends Command<U> {
	
	private PlayerController controller;
	
	public InGameCommand(String name, PlayerController controller) {
		super(name);
		this.controller = controller;
	}
	
	public InGameCommand<U> setController(PlayerController controller) {
		this.controller = controller;
		return this;
	}
	
	@Override
	public U run(String ... args) {
		if (controller.isSuppressed()) {
			return null;
		} else {
			return effect.apply(args);
		}
	}
	
}
