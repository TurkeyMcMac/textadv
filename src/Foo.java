
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.Arrays;

public final class Foo extends Being implements Solid, Graphic {
	
	char icon = ';';
	
	
	public Foo(CarDir facing, Tile tile) {
		super("Foo", "Foobarbaz!", facing, SEQUENCE, tile);
		
		// TODO Auto-generated constructor stub
	}

	private static final ArrayList<Consumer<Being>> SEQUENCE = new ArrayList<>(Arrays.asList(
		(b) -> b.step(),
		(b) ->{b.turn(RelDir.LEFT);
			   b.swapLine(0);}
	));
	
	public char getIcon() {
		return icon;
	}
	
}
