package jmp.cloud.bank.impl;

import jmp.bank.api.Bank;
import jmp.dto.*;



public class Kaspi implements Bank {


    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        if(bankCardType == BankCardType.CREDIT)
            return new CreditBankCard();
        else
            return new DebitBankCard();
    }
}
