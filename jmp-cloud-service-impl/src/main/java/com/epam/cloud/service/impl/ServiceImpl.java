package com.epam.cloud.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

/**
 * Provides an implementation of the {@link Service} interface for managing subscriptions.
 * This class allows subscribing users based on their bank card information, retrieving subscriptions
 * by bank card number or a custom predicate, and listing all users with subscriptions.
 */
public class ServiceImpl implements Service {
    // Maps users to their list of subscriptions.
    private final Map<User, List<Subscription>> storage = new HashMap<>();

    /**
     * Subscribes a user to a new subscription using their bank card.
     * A new subscription is created with the current date and added to the user's list of subscriptions.
     * If the user does not have any subscriptions, a new list is created for them.
     *
     * @param bankCard The bank card of the user to subscribe. Must not be null.
     */
    @Override
    public void subscribe(BankCard bankCard) {
        User user = bankCard.getUser();
        String number = bankCard.getNumber();
        Subscription subscription = new Subscription(number, LocalDate.now());
        storage.computeIfAbsent(user, u -> new LinkedList<>()).add(subscription);
    }

    /**
     * Retrieves a subscription by bank card number.
     * This method delegates to {@link #getSubscriptionByBankCardNumber(Predicate)} by constructing a predicate
     * that matches the subscription's bank card number with the provided card number.
     *
     * @param cardNumber The bank card number to search for a subscription. Must not be null.
     * @return An {@link Optional} containing the subscription if found, otherwise an empty {@link Optional}.
     */
    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return getSubscriptionByBankCardNumber(subscription -> subscription.bankcardNumber().equals(cardNumber));
    }

    /**
     * Retrieves a subscription that matches a given predicate.
     * This method allows for flexible searching of subscriptions based on any criteria encapsulated by the predicate.
     *
     * @param filter A {@link Predicate} to apply to each subscription for filtering. Must not be null.
     * @return An {@link Optional} containing the subscription if found, otherwise an empty {@link Optional}.
     */
    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(Predicate<Subscription> filter) {
        return storage.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(filter)
                .findAny();
    }

    /**
     * Retrieves a list of all users who have at least one subscription.
     * This method provides a way to get all users who are currently subscribed to any service.
     *
     * @return A list of all users with subscriptions. Never null.
     */
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.keySet());
    }
}
