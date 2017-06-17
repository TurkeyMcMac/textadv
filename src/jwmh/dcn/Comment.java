package jwmh.dcn;

/**
 * This class is used to
 * insert comments when
 * writing objects in
 * data capsule notation.
 * 
 * @author jude
 *
 */
public class Comment {
	
	private String text;
	
	public Comment(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
