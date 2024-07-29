package com.epam.jmp;


import com.epam.cloud.service.impl.ServiceImpl;
import com.epam.jmp.bank.api.Bank;
import com.epam.jmp.bank.impl.CentralBank;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        User userJohn = new User("John", "Smith", LocalDate.of(1990, 4, 10));
        User userAnn = new User("Ann", "Smith", LocalDate.of(1995, 10, 20));

        Bank bank = new CentralBank();
        Service service = new ServiceImpl();

        BankCard creditCard = bank.createBankCard(userJohn, BankCardType.CREDIT);
        BankCard debitCard = bank.createBankCard(userAnn, BankCardType.DEBIT);

        List<BankCard> bankCards = List.of(creditCard, debitCard);
        bankCards.stream()
                .map(card -> {
                    service.subscribe(card);
                    return service.getSubscriptionByBankCardNumber(card.getNumber())
                            .orElseThrow(() -> new RuntimeException("No subscription"));
                })
                .forEach(e -> log.info("Subscription found: {}", e));

        double averagesUsersAge = service.getAveragesUsersAge();
        log.info("Average age of users: {}", averagesUsersAge);
    }
}