package jmp.cloud.service.db.impl;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class HalykApplication implements Service {

    @Override
    public void subscribe(BankCard bankCard) {

    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return null;
    }
}
