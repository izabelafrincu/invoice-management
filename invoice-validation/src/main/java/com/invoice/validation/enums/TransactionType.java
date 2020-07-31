package com.invoice.validation.enums;

public enum TransactionType {
  IBAN_TO_IBAN {
    @Override
    public TransactionMember getPayer() {
      return TransactionMember.IBAN;
    }

    @Override
    public TransactionMember getPayee() {
      return TransactionMember.IBAN;
    }
  }, IBAN_TO_WALLET {
    @Override
    public TransactionMember getPayer() {
      return TransactionMember.IBAN;
    }

    @Override
    public TransactionMember getPayee() {
      return TransactionMember.WALLET;
    }
  }, WALLET_TO_IBAN {
    @Override
    public TransactionMember getPayer() {
      return TransactionMember.WALLET;
    }

    @Override
    public TransactionMember getPayee() {
      return TransactionMember.IBAN;
    }
  }, WALLET_TO_WALLET {
    @Override
    public TransactionMember getPayer() {
      return TransactionMember.WALLET;
    }

    @Override
    public TransactionMember getPayee() {
      return TransactionMember.WALLET;
    }
  };

  public abstract TransactionMember getPayer();

  public abstract TransactionMember getPayee();
}