package org.example;

public class AccountService {

    public void withdraw (Account acc, double value) throws InsufficientFundsException{
        if (value > acc.getBalance()) {
            double diff = value - acc.getBalance();
            throw new InsufficientFundsException("Shortfall of "+diff);
        } else{
            double diff = acc.getBalance() - value;
            acc.setBalance(diff);
        }
    }
}
