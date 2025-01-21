package com.demo.tx.utils;

import com.demo.tx.exception.InsufficientFundsException;

import java.util.HashMap;
import java.util.Map;

public class PaymemntUtils {
    private static Map<String, Double> accountBalance = new HashMap<>();

    static {
        accountBalance.put("acc1", 10000.0);
        accountBalance.put("acc2", 20000.0);
        accountBalance.put("acc3", 30000.0);
        accountBalance.put("acc4", 40000.0);
        accountBalance.put("acc5", 50000.0);
    }

    public static boolean validateCreditLimit(String accountNo, Double fare) {
        if(fare>accountBalance.get(accountNo))
            throw new InsufficientFundsException("Insufficient funds in account balance");
        else
           return true;
    }
}
