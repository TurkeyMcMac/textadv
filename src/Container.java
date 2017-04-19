
public interface Container {
	
	public boolean store(Item item);
	
	public Item fetch(Item item);
	
	public Item fetch(String name);
	/*
	public boolean store(Item item) {
		int newWeight = nowWeight + item.weight;
		int newSize = nowSize + 1;
		boolean isStorable = (newWeight <= maxWeight) && (newWeight <= maxSize);
		if (isStorable) {
			inventory[nowSize] = item;
			nowWeight = newWeight;
			nowSize = newSize;
		}
		return isStorable;
	}
	
	public Item fetch(Item item) {
		for (Item i:inventory) {
			if (i == item) {
				nowWeight -= i.getWeight();
				nowSize--;
				inventory[nowSize] = null;
				return i;
			}
		}
		return null;
	}
	
	public Item fetch(String name) {
		for (Item i:inventory) {
			if (i.name == name) {
				nowWeight -= i.getWeight();
				nowSize--;
				inventory[nowSize] = null;
				return i;
			}
		}
		return null;
	}
	*/
}
