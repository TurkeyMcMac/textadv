package jwmh.misc;

import java.util.HashSet;
import java.util.Set;

public class StringWrapper {

	int lineLength;
	String wrapped;
	
	public StringWrapper(String wrap, int wrapToLength, Set<Character> wordSplitters, Set<Character> lineRestarters) {
		StringBuilder wrapper = new StringBuilder();
		wrapToLength -= 3;
		int i = 0,
			wordBreak = 0,
			lineStart = 0,
			currentLineLength = 0;
		do {
			++currentLineLength;
			char currentChar = wrap.charAt(i);
			if (i - wordBreak + 1 >= wrapToLength || wordSplitters.contains(currentChar)) {
				wordBreak = i + 1;
			} else if (lineRestarters.contains(currentChar)) {
				currentLineLength = 0;
			}
			if (currentLineLength >= wrapToLength) {
				String line = wrap.substring(lineStart, wordBreak);
				wrapper.append(line + /*"| " + line.length() + ", " + (wrapToLength + 3) +*/ '\n');
				lineStart = wordBreak;
				currentLineLength = 0;
			}
		} while (++i < wrap.length());
		wrapper.append(wrap.substring(lineStart, i));
		lineLength = wrapToLength;
		wrapped = wrapper.toString();
	}
	
	public static final Set<Character> WORD_SPLITTERS = new HashSet<>();
	static {
		WORD_SPLITTERS.add(' ');
		WORD_SPLITTERS.add('-');
	}
	
	public static final Set<Character> LINE_RESTARTERS = new HashSet<>();
	static {
		LINE_RESTARTERS.add('\f');
		LINE_RESTARTERS.add('\n');
		LINE_RESTARTERS.add('\r');
	}
	
	public StringWrapper(String wrap, int wrapToLength) {
		this(wrap, wrapToLength, WORD_SPLITTERS, LINE_RESTARTERS);
	}
	
	public String toString() {
		return wrapped;
	}
	/*
	public static void main(String[] args) {
		for (int i = 10; i < 100; ++i) {
			System.out.println(new StringWrapper("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", i));
		}
	}
	*/
	
}
