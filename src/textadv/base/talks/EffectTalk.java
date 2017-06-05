package textadv.base.talks;

import java.util.LinkedHashMap;
import java.util.function.Consumer;

import textadv.base.outfits.Talker;

public class EffectTalk extends Talk {

	private static final long serialVersionUID = 7950078233988527905L;
	
	private Consumer<Talker> effect;
	private Talker talker;
	
	public EffectTalk(String text, 
					  LinkedHashMap<String, Talk> nextTalks,
					  Talker talker,
					  Consumer<Talker> effect) {
		super(text, nextTalks);
		this.effect = effect;
		this.talker = talker;
	}
	
	@Override
	protected void picked() {
		effect.accept(talker);
	}
	
}
