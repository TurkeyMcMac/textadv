package jwmh.dcn;

import java.io.*;
import java.util.function.Consumer;

import static java.util.AbstractMap.SimpleEntry;

/**
 * This class is used to
 * read capsules from text.
 * 
 * @author jude
 *
 */
public final class CapsuleReader {
	
	private Reader stringReader;
	private boolean open = true;
	
	/**
	 * 
	 * Creates a capsule reader
	 * which gets its information
	 * from the specified string
	 * reader.
	 * 
	 * @param stringReader
	 */
	public CapsuleReader(Reader stringReader) {
		this.stringReader = stringReader;
	}
	
	//Instantiate capsule types (must be instantiated to take effect)
	static {
		new BooleanCapsule();
		new CharacterCapsule();
		new CommentCapsule();
		new FloatCapsule();
		new GetterCapsule();
		new HashCapsule();
		new IntegerCapsule();
		new ListCapsule();
		new NullCapsule();
		new SetterCapsule();
		new StringCapsule();
	}
	
	/**
	 * Reads the next capsule.
	 * <p>
	 * Because capsules can have a
	 * value of null, one must iterate
	 * through a sequence of capsules
	 * with the {@code whileReading} 
	 * method, or if one wants, like
	 * this:
	 * <pre>
	 * {@code
	 * CapsuleReader reader = new CapsuleReader(
	 *                            new BufferedReader(
	 *                                new StringReader("['f' 'o' 'o'] ['b' 'a' 'r']")));
	 * while (true) {
	 *     Object read = reader.readCapsule();
	 *     if (reader.isReading())
	 *         doStuffWith(read);
	 *     else
	 *         break;
	 * }
	 * </pre>
	 * Both options require the reader
	 * to be {@code close}'d afterward.
	 * 
	 * @return the next capsule in the text being read
	 * @throws IOException
	 */
	public final Object readCapsule() throws IOException {
	    //iterate through capsule string
	    int place;
	    while ((place = stringReader.read()) != -1) {
	    	char currentChar = (char)place;
	    	Capsule<?> subCapsule = Capsule.capsuleStarts.get(currentChar);
	    	if (subCapsule == null) //if the character corresponds to no capsule
	    		continue;
    		Object subValue = subCapsule.evaluate(stringReader); //value of sub capsule
    		if (subCapsule instanceof Ignored) { //if this capsule should be included in the list
    			if (subCapsule instanceof SetterCapsule) { //if the capsule is a setter capsule
    				@SuppressWarnings("unchecked")
					SimpleEntry<Object, Object> var = (SimpleEntry<Object, Object>)subValue;
                	CollectiveCapsule.VARS.put(var.getKey(), var.getValue());
                }
    			continue;
    		} else { 
            	return subValue;
            }
	    }
	    open = false;
		return null;
	}
	
	/**
	 * Reads every capsule and carries
	 * out a specified consumer function
	 * that accepts each as a parameter.
	 * 
	 * @param toCarryOut the consumer to be carried out
	 * @throws IOException
	 */
	public void whileReading(Consumer<Object> toCarryOut) throws IOException {
		while (true) {
			Object read = readCapsule();
			if (open)
				toCarryOut.accept(read);
			else
				break;
		}
	}
	
	/**
	 * 
	 * @return whether or not the reader has reached the end
	 */
	public boolean isReading() {
		return open;
	}
	
	/**
	 * Closes the capsule reader.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		open = false;
		stringReader.close();
	}
	
	/**
	 * The suffix to data
	 * capsule notation
	 * files.
	 */
	public final static String SUFFIX = ".dcn";
	
}
