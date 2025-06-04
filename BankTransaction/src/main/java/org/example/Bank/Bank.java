package org.example.Bank;

import org.example.BankAccount.AccountType;
import org.example.BankAccount.BankAccount;

import java.math.BigDecimal;

public class Bank {
    private final BigDecimal vipWithdrawalLimit = BigDecimal.valueOf(5000);
    private final BigDecimal regularWithdrawalLimit = BigDecimal.valueOf(2000);
    private final BigDecimal atmWithdrawalLimit = BigDecimal.valueOf(1000);
    private final BigDecimal withdrawalFee = BigDecimal.ONE;
    private final BigDecimal paymentFee = BigDecimal.TWO;
    private final BigDecimal fastOnlineTransferFee = BigDecimal.TEN;

    public void deposit(BigDecimal amount, BankAccount bankAccount) {
        bankAccount.deposit(amount);
    }

    public boolean withdraw(BigDecimal amount, BankAccount bankAccount, WithdrawalMethod withdrawalMethod) {
        var fee = BigDecimal.ZERO;
        if(withdrawalMethod == WithdrawalMethod.ATM) {
            if(atmWithdrawalLimit.compareTo(amount) < 0) {
                return false;
            }

            fee = withdrawalFee;
        }

        if (bankAccount.getAccountType() == AccountType.VIP && amount.compareTo(vipWithdrawalLimit) > 0) {
            return false;
        }

        if (bankAccount.getAccountType() == AccountType.REGULAR && amount.compareTo(regularWithdrawalLimit) > 0) {
            return false;
        }

        return bankAccount.deduct(amount, fee);
    }

    public boolean makePayment(BigDecimal amount, BankAccount payingBankAccount, BankAccount receivingBankAccount, PaymentType paymentType, boolean fastTransfer) {
        var fee = BigDecimal.ZERO;
        if(paymentType == PaymentType.ONLINE_BANKING) {
            fee = fastTransfer ? paymentFee : fastOnlineTransferFee;
        }

        if(!payingBankAccount.deduct(amount, fee)){
            return false;
        }

        receivingBankAccount.deposit(amount);
        return true;
    }

}
