package textadv.base.control;

import java.io.Serializable;
import java.util.function.Consumer;

public interface AIOrder<T> extends Serializable, Consumer<T> {}
