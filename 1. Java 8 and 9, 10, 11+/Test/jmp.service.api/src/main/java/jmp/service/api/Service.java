package jmp.service.api;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import jmp.dto.*;

public interface Service {

    void subscribe(BankCard bankCard);
    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);
    List<User> getAllUsers();
    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);

    default double getAverageUserAge() {
        long sum = getAllUsers().stream().mapToLong(x -> ChronoUnit.YEARS.between(x.getBirthday(),LocalDate.now())).sum();
        return Double.parseDouble(new DecimalFormat("##.##").format((double)sum/getAllUsers().size()));
    }

    static boolean isPayableUser(User user) {
        long years = ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now());
        System.out.println(years);
        return years > 18;
    }
}
