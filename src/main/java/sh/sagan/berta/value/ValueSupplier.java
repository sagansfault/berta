package sh.sagan.berta.value;

import sh.sagan.berta.mutator.MutatorContext;

import java.util.Optional;

/**
 * A functional interface representing a supplied value to an unknown target given a context.
 */
@FunctionalInterface
public interface ValueSupplier<T extends MutatorContext, K> {

    /**
     * Supplies a stateful value based on the given context. If no value is wished to be supplied, empty is returned.
     *
     * @param context The context of this value supply.
     * @return An optional containing a supplied value or none if none is supplied.
     */
    Optional<K> getValue(T context);
}
