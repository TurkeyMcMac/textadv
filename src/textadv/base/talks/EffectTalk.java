package textadv.base.talks;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Consumer;

import textadv.base.outfits.Talker;

public class EffectTalk extends Talk {

	private static final long serialVersionUID = 7950078233988527905L;
	
	private Effect effect;
	private Talker talker;
	
	public EffectTalk(String text, 
					  Map<String, Talk> nextTalks,
					  Talker talker,
					  Effect effect) {
		super(text, nextTalks);
		this.effect = effect;
		this.talker = talker;
	}
	
	@FunctionalInterface
	public interface Effect extends Consumer<Talker>, Serializable {}
	
	@Override
	protected void picked() {
		effect.accept(talker);
	}
	
}
