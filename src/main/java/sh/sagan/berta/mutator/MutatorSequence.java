package sh.sagan.berta.mutator;

import java.util.Collection;

public interface MutatorSequence<T extends MutatorContext, V> extends Mutator<T, V> {

    @Override
    default V mutate(T context, V current) {
        V value = current;
        for (Mutator<T, V> mutator : getSequence()) {
            value = mutator.mutate(context, value);
        }
        return value;
    }

    Collection<Mutator<T, V>> getSequence();
}
