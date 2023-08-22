package editor.logger;

import java.util.function.Function;
import java.util.stream.Stream;

public interface Combinator<T> extends Function<Function<T, T>, Function<T, T>> {

    @SafeVarargs
    static <T> Combinator<T> combine(Function<T, T>... functions) {
        return f -> x -> f.apply(Stream.of(functions)
                .reduce(Function.identity(), Function::andThen)
                .apply(x));
    }

}
