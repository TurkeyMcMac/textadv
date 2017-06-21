package jwmh.misc;

public class StringWrapper {

	private final int lineLength;
	private final int lineCount;
	private final String wrapped;
	
	public StringWrapper(int wrapToLength, String wrap)
		throws ArithmeticException
	{
		if (wrapToLength == 0) {
			throw new ArithmeticException("/ by zero");
		}
		StringBuilder wrapper = new StringBuilder(wrap.length() + (int)Math.floor(
														(double)wrap.length() / 
														(double)wrapToLength));
		System.out.println(wrapper.capacity());
		int lines = 1,
			currentLineLength = -1;
		for (int i = 0; i < wrap.length(); ++i) {
			char currentChar = wrap.charAt(i);
			++currentLineLength;
			if (currentChar == '\n' || currentChar == '\r') {
				++lines;
				currentLineLength = -1;
			}else if (currentLineLength == wrapToLength) {
				wrapper.append('\n');
				++lines;
				currentLineLength = 0;
			}
			wrapper.append(currentChar);
		}
		System.out.println(wrapper.length());
		lineLength = wrapToLength;
		lineCount = lines;
		wrapped = wrapper.toString();
	}
	
	public String toString() {
		return wrapped;
	}
	
	public int lineCount() {
		return lineCount;
	}
	
	public int lineLength() {
		return lineLength;
	}
	
	public static void main(String[] args) {
		System.out.println(new StringWrapper(60, "    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"));
	}
	
	
}
