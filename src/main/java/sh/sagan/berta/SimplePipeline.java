package sh.sagan.berta;

import sh.sagan.berta.mutator.Mutator;
import sh.sagan.berta.mutator.MutatorContext;
import sh.sagan.berta.mutator.MutatorSequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimplePipeline<T extends MutatorContext, V> implements MutatorSequence<T, V> {

    private final List<Mutator<T, V>> mutators = new ArrayList<>();

    /**
     * Constructs a new pipeline with a list of appendable mutators.
     */
    public SimplePipeline() {}

    /**
     * Appends a mutator to this pipeline.
     *
     * @param mutator The mutator to append to this list.
     */
    public void add(Mutator<T, V> mutator) {
        this.mutators.add(mutator);
    }

    @Override
    public Collection<Mutator<T, V>> getSequence() {
        return this.mutators;
    }
}
