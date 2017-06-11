package jwmh.misc;

import java.io.Serializable;

/**
 * This class simply associated two values.
 * Pairs with the same values are treated
 * as equal.
 * 
 * @author jude
 * 
 * @param <T> the start type
 * @param <U> the end type
 */
public class Pair<T, U> implements Serializable {
	
	private static final long serialVersionUID = -8048307872092946405L;
	
	public T start;
	public U end;
	
	/**
	 * 
	 * @param start the constructed Pair's start value
	 * @param end the constructed Pair's end value
	 */
	public Pair(T start, U end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * 
	 * @param template the Pair for the constructed Pair to copy
	 */
	public Pair(Pair<T, U> template) {
		start = template.start;
		end = template.end;
	}
	
	public String toString() {
		return "(" + start.toString() + ", " + end.toString() + ")";
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
