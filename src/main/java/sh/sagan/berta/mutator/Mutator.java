package sh.sagan.berta.mutator;

/**
 * A functional interface representing a mutation that can be produced by applying itself to a given value.
 */
@FunctionalInterface
public interface Mutator<T extends MutatorContext, V> {

    /**
     * Mutates the given value, returning the mutated value. One might return the current value if they do not wish
     * to apply a mutation.
     *
     * @param context The context of this mutation.
     * @param current The current value to mutate.
     * @return The mutated value.
     */
    V mutate(T context, V current);
}
