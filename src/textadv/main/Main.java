package textadv.main;

import textadv.base.world.*;
import textadv.ui.PlayingUserInterface;
import textadv.ui.BasicUserInterface;

public final class Main {
	
	public static Grid world;
	
	public static PlayingUserInterface playerUI;
	
	private static void screen(String input) {
		System.out.println("\033c" + playerUI.tryRunFrom(input) + "\n>>");
	}
	
	public static void main(String[] args) {
		new BasicUserInterface().run(args);
		screen("welcome");
		while(true) {
			screen(System.console().readLine());
		}
	}

}
