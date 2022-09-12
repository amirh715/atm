package com.pmt.atm.utils.specification;

public interface Specification<C> {

    boolean isSatisfiedBy(C candidate);

}
