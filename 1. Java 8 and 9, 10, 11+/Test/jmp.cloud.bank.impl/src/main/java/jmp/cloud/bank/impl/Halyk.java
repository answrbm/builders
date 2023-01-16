package jmp.cloud.bank.impl;

import jmp.bank.api.Bank;
import jmp.dto.*;

import java.util.Random;

public class Halyk implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        Random generator = new Random();
        long randomNumber = (long) generator.nextInt(99999) * generator.nextInt(99999);

        return type == BankCardType.CREDIT ? new CreditBankCard(String.valueOf(randomNumber),user) : new DebitBankCard(String.valueOf(randomNumber),user);
    }
}
