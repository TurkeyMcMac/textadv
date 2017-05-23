package textadv.base.outfits;

public interface ToolUser<T extends Tool<? extends ToolUser<T>>> extends PickerUpper {
	
	public boolean wield(Tool<?> tool);
	
	public boolean unwield(Tool<?> tool);
	
}
