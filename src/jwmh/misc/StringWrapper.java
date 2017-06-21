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
	
}
