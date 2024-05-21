package sh.sagan.berta.impl;

import sh.sagan.berta.mutator.MutatorFunction;

public interface DoubleMutatorFunction extends MutatorFunction<Double> {

    DoubleMutatorFunction ADDITIVE = Double::sum;

    DoubleMutatorFunction MULTIPLICATIVE = (a, b) -> a * b;
}
