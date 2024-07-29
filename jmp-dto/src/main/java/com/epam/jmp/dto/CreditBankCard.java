package com.epam.jmp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a credit bank card, extending the generic {@link BankCard} class.
 * This class can be used to define properties and behaviors specific to credit bank cards,
 * such as credit limits, interest rates, etc., if needed in the future.
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CreditBankCard extends BankCard {
    public CreditBankCard(String number, User user) {
        super(number, user);
    }
}
