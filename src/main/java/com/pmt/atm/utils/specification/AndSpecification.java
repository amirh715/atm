package com.pmt.atm.utils.specification;

public class AndSpecification<C> extends AbstractSpecification<C> {

    private final Specification<C> first;

    private final Specification<C> second;

    public AndSpecification(Specification<C> first, Specification<C> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfiedBy(C candidate) {
        return first.isSatisfiedBy(candidate) && second.isSatisfiedBy(candidate);
    }

    public Specification<C> getFirst() {
        return this.first;
    }

    public Specification<C> getSecond() {
        return this.second;
    }

}
