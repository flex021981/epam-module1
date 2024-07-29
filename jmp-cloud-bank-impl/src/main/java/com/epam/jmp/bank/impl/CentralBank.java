package com.epam.jmp.bank.impl;

import com.epam.jmp.bank.api.Bank;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.User;

import java.util.UUID;

/**
 * Implementation of the {@link Bank} interface representing a central bank.
 * This class is responsible for creating bank cards for users based on the specified bank card type.
 */
public class CentralBank extends BankAbstract implements Bank {

    /**
     * Constructs a new CentralBank instance and initializes the creators map.
     * The creators map associates each {@link BankCardType} with a function that creates a {@link BankCard}.
     */
    public CentralBank() {
        super();
        // Associates the CREDIT and DEBIT types with their respective bank card creation functions.
        creators.put(BankCardType.CREDIT, CreditBankCard::new);
        creators.put(BankCardType.DEBIT, CreditBankCard::new);
    }

    /**
     * Creates a new bank card for a given user and bank card type.
     * Generates a unique card number and uses the creators map to create the bank card.
     *
     * @param user         The user for whom the bank card is being created.
     * @param bankCardType The type of bank card to create.
     * @return A new {@link BankCard} instance associated with the given user and card type.
     */
    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        String number = UUID.randomUUID().toString(); // Generates a unique card number.
        // Returns a new bank card created by the function associated with the specified bank card type.
        return creators.getOrDefault(bankCardType, super::trowsIfTypeIsUnknown).apply(number, user);
    }
}