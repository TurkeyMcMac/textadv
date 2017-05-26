package textadv.base.outfits;

public interface Talker extends Describable {
	
	public String talk(int index);
	
	public void endTalk();
	
	public String startTalk(String talkName);
	
	public boolean isTalking();
	
}
