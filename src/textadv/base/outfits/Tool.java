package textadv.base.outfits;

public interface Tool<T extends ToolUser<? extends Tool<T>>> extends Item {
	
	public T getUser();
	
	public void setUser(T user);
	
	public void wield(T user);
	
	public void unwield();
	
}
