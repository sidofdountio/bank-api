package com.bank_afriland.project.model.enumeration;

import lombok.Getter;

@Getter
public enum TransactionType {
    TRANSFER_OUT,
    TRANSFER_INCOMING,
    DEPOSIT,
    WITHDRAWAL,
    ATM_WITHDRAWAL,
    CASH_WITHDRAWAL,
    TAX_WITHDRAWAL
}
