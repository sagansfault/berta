package sh.sagan.berta.mutator;

@FunctionalInterface
public interface MutatorFunction<V> {

    /**
     * A function representing the operation a mutator has on two values; often the current value and the mutation value.
     * <br>
     * ie. Given two values, how does this function operate on them and return a mutated value.
     *
     * @param a One of two values this mutation involves.
     * @param b One of two values this mutation involves.
     * @return The mutated value
     */
    V apply(V a, V b);
}
