package org.example.Contracts;

import java.math.BigDecimal;

public interface Deductible {
    boolean deduct(BigDecimal amount, BigDecimal fee);
}
