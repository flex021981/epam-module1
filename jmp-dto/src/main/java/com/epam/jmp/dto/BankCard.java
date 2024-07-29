package com.epam.jmp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the abstract concept of a bank card.
 * This class is designed to be extended by specific types of bank cards.
 * It includes basic properties common to all bank cards, such as the card number and the associated user.
 */
@Getter
@AllArgsConstructor
public abstract class BankCard {
    private String number;
    private User user;
}
