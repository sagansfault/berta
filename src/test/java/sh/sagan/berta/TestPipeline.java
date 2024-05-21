package sh.sagan.berta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sh.sagan.berta.impl.DoubleMutatorFunction;
import sh.sagan.berta.mutator.MutatorContext;
import sh.sagan.berta.mutator.MutatorGroup;

import java.util.Optional;

public class TestPipeline {

    @Test
    void test() {
        MyContext context = new MyContext();
        MyPipeline myPipeline = new MyPipeline();

        double value = 6.0;
        myPipeline.getPreMultiplier().add(c -> Optional.of(3.0));
        myPipeline.getPreMultiplier().add(c -> Optional.of(4.0));
        myPipeline.getMultiplier().add(c -> Optional.of(2.0));
        myPipeline.getMultiplier().add(c -> Optional.of(5.0));
        myPipeline.add((ctx, current) -> current * 10.0);
        value = myPipeline.mutate(context, value);

        double mirror = 6.0;
        mirror += (3.0 + 4.0);
        mirror *= (2.0 + 5.0);
        mirror *= 10.0;

        Assertions.assertEquals(mirror, value);
    }

    private static final class MyContext implements MutatorContext {}

    private static final class MyPipeline extends SimplePipeline<MyContext, Double> {

        private final MutatorGroup<MyContext, Double> preMultiplier = new MutatorGroup<>(
                PipelineTypeSupplier.EMPTY_DOUBLE,
                DoubleMutatorFunction.ADDITIVE,
                DoubleMutatorFunction.ADDITIVE
        );
        private final MutatorGroup<MyContext, Double> multiplier = new MutatorGroup<>(
                PipelineTypeSupplier.EMPTY_DOUBLE,
                DoubleMutatorFunction.MULTIPLICATIVE,
                DoubleMutatorFunction.ADDITIVE
        );

        public MyPipeline() {
            super();
            super.add(preMultiplier);
            super.add(multiplier);
        }

        public MutatorGroup<MyContext, Double> getPreMultiplier() {
            return preMultiplier;
        }

        public MutatorGroup<MyContext, Double> getMultiplier() {
            return multiplier;
        }
    }
}
