package com.epam.jmp.bank.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Abstract class representing a bank with basic functionalities.
 * This class provides a foundation for specific bank implementations,
 * including a map of bank card creators and a method to handle unknown bank card types.
 */
public abstract class BankAbstract {
    // Maps bank card types to their respective creators (functions that create bank cards).
    protected final Map<BankCardType, BiFunction<String, User, BankCard>> creators;

    /**
     * Constructs a new BankAbstract instance and initializes the creators map.
     * The creators map associates each {@link BankCardType} with a function that creates a {@link BankCard}.
     */
    protected BankAbstract() {
        creators = new EnumMap<>(BankCardType.class);
    }

    /**
     * Throws an exception if the bank card type is unknown.
     * This method is used as a default action in the creators map when an unknown bank card type is requested.
     *
     * @param number The card number (unused in this context).
     * @param user   The user for whom the card is being created (unused in this context).
     * @throws IllegalArgumentException if the bank card type is unknown.
     */
    protected BankCard trowsIfTypeIsUnknown(String number, User user) {
        throw new IllegalArgumentException("Unknown bank card type");
    }
}
