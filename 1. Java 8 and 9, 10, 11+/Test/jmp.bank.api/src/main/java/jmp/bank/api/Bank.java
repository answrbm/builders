package jmp.bank.api;

import jmp.dto.*;

public interface Bank {

    BankCard createBankCard(User user, BankCardType type);
}
