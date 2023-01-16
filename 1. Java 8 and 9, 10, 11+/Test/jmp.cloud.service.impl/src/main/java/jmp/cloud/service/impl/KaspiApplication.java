package jmp.cloud.service.impl;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class KaspiApplication implements Service {

    private final String path = "KaspiApplication" + File.separator + "bankCards.txt";
    private final File dir = new File("C:\\KaspiApp");
    private final File bankCards = new File("C:\\KaspiApp\\bankCards.txt");

    @Override
    public void subscribe(BankCard bankCard) {
        try(var writer = new BufferedWriter(new FileWriter(bankCards,true))) {
            User user = bankCard.getUser();
            LocalDate birthDate = user.getBirthday();
            int bDay = birthDate.getDayOfMonth();
            int bMonth = birthDate.getMonthValue();
            int bYear = birthDate.getYear();
            String cardNumber = bankCard.getNumber();
            LocalDate startDate = LocalDate.now();
            Subscription subscription = new Subscription(cardNumber,startDate);
            int day = startDate.getDayOfMonth();
            int month = startDate.getMonthValue();
            int year = startDate.getYear();
            writer.write(user.getName() + " " + user.getSurname() + " " + String.format("%s.%s.%s",bDay,bMonth,bYear) + " " +
                    subscription.getBankcard() + " " + String.format("%s.%s.%s",day,month,year) + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        try(var reader = new BufferedReader(new FileReader(bankCards))) {
            Optional<String> optional = Optional.ofNullable(reader.lines().filter(x -> x.split(" ")[3].equals(cardNumber)).findFirst().orElseThrow(() -> new Exception("No such subscription!")));
            if(optional.isPresent()) {
                var subscription = optional.get().split(" ");
                var stringStartDate = subscription[4].split("\\.");
                int day = Integer.parseInt(stringStartDate[0]);
                int month = Integer.parseInt(stringStartDate[1]);
                int year = Integer.parseInt(stringStartDate[2]);
                LocalDate startDate = LocalDate.of(year,month,day);
                String bankCard = subscription[3];
                var result = new Subscription(bankCard,startDate);
                return Optional.of(result);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(var reader = new BufferedReader(new FileReader(bankCards))) {
            return reader.lines().map(x -> {
                String[] userArr = x.split(" ");
                String name = userArr[0];
                String surname = userArr[1];
                String[] birthDate = userArr[2].split("\\.");
                int day = Integer.parseInt(birthDate[0]);
                int month = Integer.parseInt(birthDate[1]);
                int year = Integer.parseInt(birthDate[2]);
                LocalDate birthday = LocalDate.of(year,month,day);
                return new User(name,surname,birthday);
            }).collect(Collectors.toUnmodifiableList());
        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public Subscription subscriptionConverter(String line) {
        var subscription = line.split(" ");
        var stringStartDate = subscription[4].split("\\.");
        int day = Integer.parseInt(stringStartDate[0]);
        int month = Integer.parseInt(stringStartDate[1]);
        int year = Integer.parseInt(stringStartDate[2]);
        LocalDate startDate = LocalDate.of(year,month,day);
        String bankCard = subscription[3];
        return new Subscription(bankCard,startDate);
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        try(var reader = new BufferedReader(new FileReader(bankCards))) {
            return reader.lines().filter(x -> {
                Subscription subscription = subscriptionConverter(x);
                return condition.test(subscription);
            }).map(this::subscriptionConverter).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
