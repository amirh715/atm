package com.pmt.atm.utils.specification;

public class NotSpecification<C> extends AbstractSpecification<C> {

    private final Specification<C> spec;

    public NotSpecification(Specification<C> spec) {
        this.spec = spec;
    }

    @Override
    public boolean isSatisfiedBy(C candidate) {
        return !spec.isSatisfiedBy(candidate);
    }

    public Specification<C> getSpec() {
        return spec;
    }

}
