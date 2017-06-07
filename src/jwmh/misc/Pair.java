package jwmh.misc;

public class Pair<T, U> {
	
	public T start;
	public U end;
	
	public Pair(T start, U end) {
		this.start = start;
		this.end = end;
	}
	
	public Pair(Pair<T, U> template) {
		start = template.start;
		end = template.end;
	}
	
	public String toString() {
		return "(" + start + ", " + end + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		return hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		return start.hashCode() ^ end.hashCode();
	}
	
}
