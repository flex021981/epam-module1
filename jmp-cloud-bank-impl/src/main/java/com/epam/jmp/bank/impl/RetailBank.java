package com.epam.jmp.bank.impl;

import com.epam.jmp.bank.api.Bank;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.User;

import java.util.UUID;

/**
 * Implementation of the {@link Bank} interface representing a retail bank.
 * This class is responsible for creating bank cards for users based on the specified bank card type.
 */
public class RetailBank extends BankAbstract implements Bank {

    /**
     * Constructs a new RetailBank instance and initializes the creators map.
     * The creators map associates the DEBIT {@link BankCardType} with a function that creates a {@link CreditBankCard}.
     */
    public RetailBank() {
        super();
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
        String number = UUID.randomUUID().toString();
        return creators.getOrDefault(bankCardType, super::trowsIfTypeIsUnknown).apply(number, user);
    }
}
