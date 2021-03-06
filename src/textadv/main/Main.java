package textadv.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import jwmh.misc.StringWrapper;
import textadv.base.world.*;
import textadv.ui.PlayingUserInterface;
import textadv.ui.BasicUserInterface;

public final class Main {
	
	public static Grid world;
	
	public static PlayingUserInterface playerUI;
	
	private static String title;
	
	static {
		try {
			BufferedReader titleReader = new BufferedReader(new FileReader("src/resources/title.txt"));
			StringBuilder readTitle = new StringBuilder();
			String line;
			while ((line = titleReader.readLine()) != null) {
				readTitle.append(line + '\n');
			}
			titleReader.close();
			title = readTitle.toString();
		} catch (IOException e) {
			title = "TEXTADV\n";
		}
	}
	
	private static void screen(String input) {
		System.out.println("\033c" + title + '\n' + new StringWrapper(60, playerUI.tryRunFrom(input) + '\n' + world.getAlerts() + "\n>>"));
	}
	
	public static void main(String[] args) {
		new BasicUserInterface().run(args);
		screen("welcome");
		while(true) {
			screen(System.console().readLine());
		}
	}

}
