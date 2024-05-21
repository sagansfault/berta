package sh.sagan.berta.mutator;

import sh.sagan.berta.PipelineTypeSupplier;
import sh.sagan.berta.value.ValueSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A class representing a sequence or grouping of mutators that are all to be compacted or applied in a specified way.
 * <br>
 * A common example of this is organizing pre-multiplier flat bonuses in a {@code Double} pipeline as well as
 * additive multiplier mutators and multiplicative multipliers all into their sequences such that one can separate and
 * organize such computations (pseudo):
 * <pre>
 * {@code
 * double value = 0;
 * // pre-multiplier
 * value += 1 + 1.43 + 8;
 * // additive-multiplier
 * value *= 2.5 + 0.2 + 1.0;
 * // multiplicative-multiplier
 * value *= 8 * 2 * 0.5;
 * }
 * </pre>
 */
public class MutatorGroup<T extends MutatorContext, V> implements Mutator<T, V> {

    private final List<ValueSupplier<T, V>> group = new ArrayList<>();

    private final MutatorFunction<V> groupMutatorFunction;
    private final ValueSupplier<T, V> groupValueSupplier;

    /**
     * Constructs a new mutator group.
     *
     * @param typeSupplier A type supplier for this group's compacting.
     * @param groupMutatorFunction The function this group uses to apply its compacted value to a pipeline.
     * @param compactor The function this pipeline uses to compact its values for a single mutation.
     */
    public MutatorGroup(PipelineTypeSupplier<V> typeSupplier, MutatorFunction<V> groupMutatorFunction, MutatorFunction<V> compactor) {
        this.groupMutatorFunction = groupMutatorFunction;
        this.groupValueSupplier = context -> {
            V value = typeSupplier.getEmpty();
            for (ValueSupplier<T, V> supplier : group) {
                Optional<V> suppliedOpt = supplier.getValue(context);
                if (suppliedOpt.isPresent()) {
                    value  = compactor.apply(value, suppliedOpt.get());
                }
            }
            return Optional.of(value);
        };
    }

    /**
     * Appends a value supplier to this sequence to be compacted and applied by the group.
     *
     * @param valueSupplier The value supplier to add.
     */
    public void add(ValueSupplier<T, V> valueSupplier) {
        this.group.add(valueSupplier);
    }

    @Override
    public V mutate(T context, V current) {
        return this.groupValueSupplier.getValue(context).map(v -> groupMutatorFunction.apply(v, current)).orElse(current);
    }
}
