package jwmh.dcn;

import java.util.Map;

import jwmh.dcn.exceptions.UnparseableCapsuleException;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

abstract class Capsule<T> {

	final char START;
	final char FINISH;
	final String SELECTOR;
	
	protected Capsule(Character start, Character finish, String selector) {
		START = start;
		if (finish == null) {
			FINISH = ';';
		} else {
			FINISH = finish;
		}
		if (selector == null) {
			String capsuleName = getClass().getSimpleName().toLowerCase();
			SELECTOR = capsuleName.substring(0, capsuleName.length() - 7);
		} else {
			SELECTOR = selector;
		}
		capsuleBorders.put(start, finish);
		capsuleStarts.put(start, this);
		capsules.add(this);
	}
	
	protected static Map<Character, Character> capsuleBorders = new HashMap<>();
	protected static Map<Character, Capsule<?>> capsuleStarts = new HashMap<>();
	static List<Capsule<?>>  capsules = new ArrayList<>();
	
	static String doStringify(Object anObject) {
		for (Capsule<?> c : capsules) {
			if (c.matches(anObject)) {
				return c.stringify(anObject);
			}
		}
		throw new UnparseableCapsuleException(anObject.toString());
	}
	
	protected abstract ValueEnd evaluate(String capsule);
	
	protected abstract String stringify(Object anObject);
	
	protected abstract boolean matches(Object anObject);
	
	//a data structure containing the evaluated contents of a capsule and where in the string it ends
	protected final class ValueEnd {
	    
	    T value;
	    int terminator;
	    
	    ValueEnd(T value, int terminator) {
	        this.value = value;
	        this.terminator = terminator + 1;
	    }
	    
	}
	
}
