package org.example.RecordingStudio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RecordingStudio extends Studio {
    private BigDecimal priceInBGN;
    private int maxHoursPerDay;
    private int rentedHours;
    private double exchangeRateEURtoBGN;

    public RecordingStudio(int id, BigDecimal priceInBGN, BigDecimal minPriceInBGN, int maxHoursPerDay, double exchangeRateEURtoBGN) {
        super(id);
        this.exchangeRateEURtoBGN = exchangeRateEURtoBGN;
        this.rentedHours = 0;
        setPrice(priceInBGN);
        setMaxHoursPerDay(maxHoursPerDay);
    }

    private void setMaxHoursPerDay(int maxHoursPerDay) {
        int MAX_HOURS_PER_DAY_LIMIT = 24;
        if (maxHoursPerDay < 0 || maxHoursPerDay > MAX_HOURS_PER_DAY_LIMIT) {
            throw new IllegalArgumentException("Max rental hours per day must be between 0 and " + MAX_HOURS_PER_DAY_LIMIT +".");
        }
        this.maxHoursPerDay = maxHoursPerDay;
    }

    private void setExchangeRateEURtoBGN(double exchangeRate) {
        if (exchangeRate <= 0) {
            throw new IllegalArgumentException("Exchange rate must be positive.");
        }
        exchangeRateEURtoBGN = exchangeRate;
    }

    @Override
    public void setRentedHours(int rentedHours) {
        if (rentedHours < 0 || rentedHours > maxHoursPerDay) {
            throw new IllegalArgumentException("Rented hours must be between 0 and maxHoursPerDay.");
        }
        this.rentedHours = rentedHours;
    }

    @Override
    public void setPrice(BigDecimal priceInBGN) {
        if (priceInBGN.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Rental price must be positive.");
        }
        if(priceInBGN.compareTo(MIN_PRICE_IN_BGN) < 0){
            this.priceInBGN = MIN_PRICE_IN_BGN;
        }
        else{
            this.priceInBGN  = priceInBGN;
        }
    }

    @Override
    public BigDecimal getIncomeInBGN() {
        return priceInBGN.multiply(BigDecimal.valueOf(rentedHours));
    }

    @Override
    public BigDecimal getIncomeInEUR() {
        return getIncomeInBGN().divide(BigDecimal.valueOf(exchangeRateEURtoBGN), 6, RoundingMode.HALF_EVEN);
    }

    @Override
    public int compareTo(RecordingStudio otherStudio) {
        return this.getIncomeInBGN().compareTo(otherStudio.getIncomeInBGN());
    }
}

