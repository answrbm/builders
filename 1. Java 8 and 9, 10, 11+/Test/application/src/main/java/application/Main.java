package application;

import jmp.dto.BankCard;
import jmp.dto.User;
import jmp.service.api.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) throws Exception {
        ServiceLoader<Service> serviceLoader = ServiceLoader.load(Service.class);

        Optional<Service> optional = serviceLoader.findFirst();
        optional.orElseThrow(() -> new Exception("No service providers were found!"));
        Service kaspiApplication = optional.get();

        LocalDate bd1 = LocalDate.of(1994,3,5);
        User user1 = new User("Name1","Surname1",bd1);
        BankCard bankCard1 = new BankCard("123456",user1);

        LocalDate bd2 = LocalDate.of(2005,5,25);
        User user2 = new User("Name2","Surname2",bd2);
        BankCard bankCard2 = new BankCard("7891011",user2);

        LocalDate bd3 = LocalDate.of(1998,1,15);
        User user3 = new User("Name3","Surname3",bd3);
        BankCard bankCard3 = new BankCard("654321",user3);

        kaspiApplication.subscribe(bankCard1);
        kaspiApplication.subscribe(bankCard2);
        kaspiApplication.subscribe(bankCard3);

        System.out.println(kaspiApplication.getSubscriptionByBankCardNumber(bankCard1.getNumber()).get().getBankcard());
        System.out.println(kaspiApplication.getAllUsers());
        System.out.println(kaspiApplication.getAverageUserAge());
        System.out.println(Service.isPayableUser(user3));

    }
}
