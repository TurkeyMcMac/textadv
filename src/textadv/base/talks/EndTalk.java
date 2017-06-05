package textadv.base.talks;

import java.util.LinkedHashMap;

import textadv.base.outfits.Talker;

public final class EndTalk extends Talk {
	
	private static final long serialVersionUID = -4254007465755123791L;

	Talker endee;
	
	public static final String END_MESSAGE = "This conversation is over.";
	
	public EndTalk(String text, Talker endee) {
		super(text.isEmpty() ?
			      END_MESSAGE : 
			      text + '\n' + END_MESSAGE, 
			  new LinkedHashMap<String, Talk>());
		this.endee = endee;
	}
	
	@Override
	protected void picked() {
		endee.endTalk();
	}
	
}
