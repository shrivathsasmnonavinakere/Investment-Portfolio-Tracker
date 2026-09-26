package com.investment.tracker.exception;

public class InvestmentNotFoundException extends RuntimeException {

    public InvestmentNotFoundException(Long id) {
        super("Investment not found with id: " + id);
    }
}
