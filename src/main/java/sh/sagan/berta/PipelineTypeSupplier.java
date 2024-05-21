package sh.sagan.berta;

/**
 * A class representing a simple identity supplier until for a given type.
 */
@FunctionalInterface
public interface PipelineTypeSupplier<V> {

    PipelineTypeSupplier<Double> EMPTY_DOUBLE = () -> 0.0;

    /**
     * @return A default initialized value to be used as 'zero' in a pipeline's context.
     */
    V getEmpty();
}
