package org.example.BankAccount;

import org.example.Bank.Bank;
import org.example.Contracts.Depositable;
import org.example.Contracts.Deductible;
import org.example.Owner.Owner;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankAccount implements Depositable, Deductible {
    public BankAccount(String iban, BigDecimal balance, LocalDate creationDate, AccountType accountType, Bank bank, Owner owner) {
        this.iban = iban;
        this.balance = balance;
        this.creationDate = creationDate;
        this.accountType = accountType;
        this.bank = bank;
        this.owner = owner;
    }

    private final String iban;
    private final LocalDate creationDate;
    private final AccountType accountType;
    private final Bank bank;
    private final Owner owner;
    private BigDecimal balance;

    public String getIban() {
        return iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Bank getBank() {
        return bank;
    }

    public Owner getOwner() {
        return owner;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    @Override
    public boolean deduct(BigDecimal amount, BigDecimal fee) {
        if (this.balance.subtract(amount).subtract(fee).compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        this.balance = this.balance.subtract(amount).subtract(fee);
        return true;
    }
}
