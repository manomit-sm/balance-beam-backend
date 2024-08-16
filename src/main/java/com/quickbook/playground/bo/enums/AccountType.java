package com.quickbook.playground.bo.enums;

public enum AccountType {
    BANK("Bank"),
    ACCOUNTS_RECEIVABLE("Accounts Receivable"),
    OTHER_CURRENT_ASSET("Other Current Asset"),
    FIXED_ASSET("Fixed Asset"),
    OTHER_ASSET("Other Asset"),
    ACCOUNTS_PAYABLE("Accounts Payable"),
    CREDIT_CARD("Credit Card"),
    OTHER_CURRENT_LIABILITY("Other Current Liability"),
    LONG_TERM_LIABILITY("Long Term Liability"),
    EQUITY("Equity"),
    INCOME("Income"),
    COST_OF_GOODS_SOLD("Cost of Goods Sold"),
    EXPENSE("Expense"),
    OTHER_INCOME("Other Income"),
    OTHER_EXPENSE("Other Expense"),
    NON_POSTING("Non-Posting");

    private final String value;
    AccountType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static AccountType fromValue(String v) {
        for (int i = 0; i < values().length; i++) {
            final AccountType accountType = values()[i];
            if (accountType.value.equals(v))
                return accountType;
        }
        throw new IllegalArgumentException(v);
    }
}
