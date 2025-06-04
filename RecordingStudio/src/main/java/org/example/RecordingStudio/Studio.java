package org.example.RecordingStudio;

import org.example.Contracts.IncomeCalculable;

import java.math.BigDecimal;

public abstract class Studio implements IncomeCalculable, Comparable<RecordingStudio> {
    protected final BigDecimal MIN_PRICE_IN_BGN = BigDecimal.TEN;
    protected final int id;

    public Studio(int id) {
        this.id = id;
    }

    public abstract void setRentedHours(int rentedHours);
    public abstract void setPrice(BigDecimal priceInBGN);
}
