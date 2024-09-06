package com.quickbook.playground.models;

import lombok.Getter;

@Getter
public class TransactionListResponse {
    private final String transactionDate;
    private final String transactionType;
    private final String documentNumber;
    private final String isPosting;
    private final String name;
    private final String description;
    private final String accountName;
    private final String split;
    private final String amount;
    private final String transactionTypeId;

    private TransactionListResponse(Builder builder) {
        this.accountName = builder.accountName;
        this.isPosting = builder.isPosting;
        this.amount = builder.amount;
        this.transactionDate = builder.transactionDate;
        this.transactionType = builder.transactionType;
        this.description = builder.description;
        this.documentNumber = builder.documentNumber;
        this.name = builder.name;
        this.split = builder.split;
        this.transactionTypeId = builder.transactionTypeId;
    }

    public static class Builder {
        private String transactionDate;
        private String transactionType;
        private String documentNumber;
        private String isPosting;
        private String name;
        private String description;
        private String accountName;
        private String split;
        private String amount;
        private String transactionTypeId;

        public Builder setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public Builder setTransactionType(String transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public Builder setDocumentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder setPosting(String posting) {
            isPosting = posting;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Builder setSplit(String split) {
            this.split = split;
            return this;
        }

        public Builder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder setTransactionTypeId(String transactionTypeId) {
            this.transactionTypeId = transactionTypeId;
            return this;
        }
        public TransactionListResponse build(){
            return new TransactionListResponse(this);
        }
    }
}
