package jwmh.dcn;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

import jwmh.dcn.exceptions.VariableNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is the parent
 * of all capsule types which
 * contain other capsules.
 * <p>
 * Note: the starts and finishes
 * of collective capsules cannot
 * be the same
 * 
 * @author jude
 *
 * @param <T> the type contained in a capsule
 */
abstract class CollectiveCapsule<T> extends Capsule<T> {
	
	/**
	 * The map from variable keys to variable values
	 */
	private static final Map<Object, Object> VARS = new HashMap<>();
	
	protected CollectiveCapsule(Character start, Character finish, String selector) {
		super(start, finish, selector);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected final ValueEnd evaluate(String capsule) {
		//list of variables marked as local
		Set<Object> localVars = new HashSet<>();
		//list of processed values, to be filled
	    List<Object> valueList = new ArrayList<>();
	    //the position of the end of the capsule, to be determined
	    int terminator = 0;
	    //iterate through capsule string
	    for (int i = 0; i < capsule.length(); i++) {
	        char currentChar = capsule.charAt(i);
	        //check if currentChar is the beginning of a sub-capsule
	        Capsule<?> subCapsule = capsuleStarts.get(currentChar);
	        if (subCapsule != null) {
	            ValueEnd subValue = (Capsule<T>.ValueEnd) subCapsule.evaluate(capsule.substring(i + 1));
	            if (!(subCapsule instanceof Ignored)) {
	            	if (subCapsule instanceof GetterCapsule) {
	            		if (VARS.containsKey(subValue.value)) {
	            			Object varValue = VARS.get(subValue.value);
	            			valueList.add(varValue);
	            		} else {
	            			throw new VariableNotFoundException(subValue.value.toString());
	            		}
	            	} else {
	            		valueList.add(subValue.value);
	            	}
	            } else if (subCapsule instanceof SetterCapsule) {
	            	AbstractMap.SimpleEntry<Object, Object> var = (SimpleEntry<Object, Object>)subValue.value;
	            	Object name = var.getKey();
	            	Object value = var.getValue();
	            	if (VARS.containsKey(name)) {
	            		VARS.put(name, value);
	            	} else {
	            		VARS.put(name, value);
	            		localVars.add(name);
	            	}
	            }
	            i += subValue.terminator;
	        } else if (currentChar == capsuleBorders.get(START)) {
	        	terminator = i;
	        	break;
	        }
	    }
	    for (Object v : localVars) {
	    	VARS.remove(v);
	    }
	    return new ValueEnd(processList((List<Object>)valueList), terminator);
	}
	
	/**
	 * Processes the list of values
	 * collected into a different
	 * data structure.
	 * 
	 * @param valueList the list of values
	 * @return the new data structure
	 */
	protected abstract T processList(List<Object> valueList);
	
}
