package jwmh.dcn;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class contains static
 * methods for reading capsules
 * from and writing capsules
 * to files.
 * 
 * @author jude
 *
 */
public final class Capsules {
	
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
	 * The suffix to all
	 * data capsule
	 * notation files.
	 */
	public final static String SUFFIX = ".dcn";
	
	/**
	 * A map from capsule
	 * selectors to capsules
	 * themselves.
	 */
	protected static Map<String, Capsule<?>> capsuleIds = new HashMap<>();
	
	static {
		List<Capsule<?>> capsules = Capsule.capsules;
		for (Capsule<?> c : capsules) {
			capsuleIds.put(c.SELECTOR, c);
		}
	}
	
	/**
	 * A list of capsule
	 * selectors.
	 */
	public static final Set<String> SELECTORS = capsuleIds.keySet();
	
	/**
	 * Reads a string as a
	 * specified capsule.
	 * 
	 * @param text the text to be evaluated
	 * @param capsuleId the selector of the capsule that the text should be evaluated as
	 * @return the object evaluated from the text
	 */
	public static Object read(String text, String capsuleId) {
		Capsule<?> capsule = capsuleIds.get(capsuleId);
		return capsule.evaluate(text + capsule.FINISH).value;
	}
	
	/**
	 * Reads a string as a
	 * hash capsule.
	 * 
	 * @param text the text to be evaluated
	 * @return the map evaluated from the text
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> readHash(String text) {
		return (Map<Object, Object>)read(text, "hash");
	}
	
	/**
	 * Reads a string as a
	 * list capsule.
	 * 
	 * @param text the text to be evaluated
	 * @return the list evaluated from the text
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> readList(String text) {
		return (List<Object>)read(text, "list");
	}
	/**
	 * Reads an object from
	 * a file represented as
	 * a capsule.
	 * <p>
	 * This method assumes
	 * that the file ends
	 * with ".dcn".
	 * 
	 * @param the path of the file to be read from
	 * @return the object evaluated from the text
	 */
	public static Object readFile(String path, String capsuleId) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path + SUFFIX));
		return read(new String(encoded, StandardCharsets.UTF_8), capsuleId);
	}
	
	/**
	 * Reads a map from a
	 * file represented as
	 * a hash capsule.
	 * <p>
	 * This method assumes
	 * that the file ends
	 * with ".dcn".
	 * 
	 * @param path
	 * @return the map evaluated
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> readHashFile(String path) throws IOException {
		return (Map<Object, Object>)readFile(path, "hash");
	}
	
	/**
	 * Reads a list from a
	 * file represented as
	 * a list capsule.
	 * <p>
	 * This method assumes
	 * that the file ends
	 * with ".dcn".
	 * 
	 * @param path
	 * @return the list evaluated
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> readListFile(String path) throws IOException {
		return (List<Object>)readFile(path, "list");
	}
	
	/**
	 * Writes an object to
	 * a string as the
	 * corresponding capsule.
	 * 
	 * @param anObject the object to be stringified
	 * @return the stringified object
	 */
	public static String write(Object anObject) {
		return Capsule.doStringify(anObject);
	}
	
	/**
	 * Writes an object to a
	 * file as the corresponding
	 * capsule.
	 * <p>
	 * This method assumes
	 * that the file ends
	 * with ".dcn".
	 * 
	 * @param path the path of the file
	 * @param anObject the object to be stringified
	 * @throws IOException
	 */
	public static void writeFile(String path, Object anObject) throws IOException {
		String notice = "`This file has been auto-generated.`\n";
		String stringified = write(anObject); //get string corresponding to object
		stringified = stringified.substring(1, stringified.length() - 1); //remove capsule borders
		Writer writer = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(path + SUFFIX), "utf-8")); //writer to file
		writer.write(notice);
		BufferedReader reader = new BufferedReader(new StringReader(stringified));
		String line;
		while ((line = reader.readLine()) != null) { //remove tabs at the beginning of lines
			String toBeWritten;
			if (line.length() > 0 && line.charAt(0) == '\t') {
				toBeWritten = line.substring(1);
			} else {
				toBeWritten = line;
			}
			writer.write(toBeWritten + '\n');
		}
		writer.close();
	}
	
	//for testing:
	//public static void main(String[] args) {}
	
}
