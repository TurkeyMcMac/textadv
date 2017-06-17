package jwmh.dcn;

import java.io.IOException;
import java.io.Writer;

/**
 * This class is used to
 * write capsules to text.
 * 
 * @author jude
 *
 */
public class CapsuleWriter {
	
	private Writer stringWriter;
	
	/**
	 * 
	 * Creates a capsule writer
	 * which sends its information
	 * to the specified destination.
	 * 
	 * @param stringReader
	 */
	public CapsuleWriter(Writer stringWriter) {
		this.stringWriter = stringWriter;
	}
	
	/**
	 * Closes the writer.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		stringWriter.close();
	}
	
	/**
	 * Writes an object as data capsule
	 * notation to the destination of
	 * the writer.
	 * 
	 * @param anObject
	 * @throws NoCorrespondingCapsuleException
	 * @throws IOException
	 */
	public void writeCapsule(Object anObject) throws
		NoCorrespondingCapsuleException, IOException {
		stringWriter.write(Capsule.doStringify(anObject) + '\n');
	}
	
}
