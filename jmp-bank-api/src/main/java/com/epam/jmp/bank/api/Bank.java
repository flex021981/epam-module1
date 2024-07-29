package com.epam.jmp.bank.api;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;

/**
 * Defines the contract for bank operations related to bank cards.
 * This interface outlines the capabilities that a bank must provide,
 * specifically focusing on the creation of bank cards for users.
 */
public interface Bank {
    /**
     * Creates a new bank card for a given user and card type.
     * This method is responsible for generating a bank card instance
     * based on the specified user and the type of bank card requested.
     *
     * @param user The user for whom the bank card is being created.
     * @param bankCardType The type of bank card to create (e.g., CREDIT, DEBIT).
     * @return A new {@link BankCard} instance associated with the given user and card type.
     */
    BankCard createBankCard(User user, BankCardType bankCardType);
}
