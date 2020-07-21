package br.com.cvc.calculator.impl;

import br.com.cvc.calculator.Calculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ChildCalculator implements Calculator {

    @Value("${child.commission}")
    private double commission;

    @Override
    public BigDecimal calc(long days, double basePrice, Integer numberOfChildren) {
        return new BigDecimal((days  * basePrice * numberOfChildren ) / commission);
    }
}
