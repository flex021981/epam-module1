package com.epam.jmp.dto;

import java.time.LocalDate;

/**
 * Represents a credit bank card, extending the generic {@link BankCard} class.
 * This class can be used to define properties and behaviors specific to credit bank cards,
 * such as credit limits, interest rates, etc., if needed in the future.
 */
public record Subscription(String bankcardNumber, LocalDate startDate) {
}
