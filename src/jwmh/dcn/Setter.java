package jwmh.dcn;

/**
 * This class is used to
 * insert variable setters
 * when writing objects in
 * data capsule notation.
 * <p>
 * Warning: do not try to
 * use capsules which will
 * be ignored on compilation
 * as variable keys or values;
 * an IllegalContentsException
 * will be thrown.
 * 
 * @author jude
 *
 */
public class Setter {
	
	Object key;
	Object value;
	
	public Setter(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

}
