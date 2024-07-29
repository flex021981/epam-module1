package com.epam.jmp.service.api;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Defines the contract for subscription-related services.
 */
public interface Service {
    /**
     * Subscribes a user to a service using their bank card.
     *
     * @param bankCard The bank card associated with the user to be subscribed.
     */
    void subscribe(BankCard bankCard);

    /**
     * Retrieves a subscription by the bank card number.
     *
     * @param cardNumber The bank card number to search for a subscription.
     * @return An {@link Optional} containing the subscription if found, otherwise an empty {@link Optional}.
     */
    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    /**
     * Retrieves a subscription that matches a given predicate.
     *
     * @param filter A {@link Predicate} to apply to each subscription for filtering.
     * @return An {@link Optional} containing the subscription if found, otherwise an empty {@link Optional}.
     */
    Optional<Subscription> getSubscriptionByBankCardNumber(Predicate<Subscription> filter);

    /**
     * Retrieves a list of all users who have subscriptions.
     *
     * @return A list of all users with subscriptions.
     */
    List<User> getAllUsers();

    /**
     * Calculates the average age of all users with subscriptions.
     * This method uses the birthday of each user to calculate their age in days,
     * then averages these values. If there are no users, it returns 0.
     *
     * @return The average age of users in days as a double. If no users, returns 0.
     */
    default double getAveragesUsersAge() {
        return getAllUsers().stream()
                .mapToDouble(user -> user.birthday().toEpochDay())
                .average()
                .orElse(0);
    }

    /**
     * Determines if a user is eligible for making payments.
     * A user is considered eligible if they are 18 years or older.
     *
     * @param user The user to check for payment eligibility.
     * @return true if the user is 18 years or older, false otherwise.
     */
    static boolean isPayableUser(User user) {
        return user.birthday().isBefore(LocalDate.now().minusYears(18));
    }
}
