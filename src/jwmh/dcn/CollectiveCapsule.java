package jwmh.dcn;

import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
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
	static final Map<Object, Object> VARS = new HashMap<>();
	
	protected CollectiveCapsule(Character start, Character finish) {
		super(start, finish);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected final T evaluate(Reader reader)
			throws IOException {
		//list of variables marked as local
		Set<Object> localVars = new HashSet<>();
		//list of processed values, to be filled
	    List<Object> valueList = new ArrayList<>();
	    //iterate through capsule string
	    int place;
	    while ((place = reader.read()) != -1) {
	    	char currentChar = (char)place;
	    	if (currentChar == FINISH) break;
	    	Capsule<?> subCapsule = capsuleStarts.get(currentChar);
	    	if (subCapsule != null) { //if the character corresponds to a capsule
	    		Object subValue = subCapsule.evaluate(reader); //value of sub capsule
	    		if (!(subCapsule instanceof Ignored)) { //if this capsule should be included in the list
	            	valueList.add(subValue);
	            } else if (subCapsule instanceof SetterCapsule) { //if the capsule is a setter capsule
	            	AbstractMap.SimpleEntry<Object, Object> var = (SimpleEntry<Object, Object>)subValue;
	            	Object varName = var.getKey();
	            	Object varValue = var.getValue();
	            	if (VARS.containsKey(varName)) { //if the setter is setting a higher-scoped variable
	            		VARS.replace(varName, varValue);
	            	} else { //create local variable
	            		VARS.put(varName, varValue);
	            		localVars.add(varName);
	            	}
	            }
	    	}
	    }
	    for (Object v : localVars) { //remove local variables
	    	VARS.remove(v);
	    }
	    return processList((List<Object>)valueList);
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
