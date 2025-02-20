package com.bank_afriland.project.model.enumeration;

import lombok.Getter;

@Getter
public enum TransferType {
    INTERNAL_TRANSFER,     // Between accounts in same bank
    EXTERNAL_TRANSFER,     // To other banks
    SCHEDULED_TRANSFER,    // Future dated transfer
    BULK_TRANSFER
}
