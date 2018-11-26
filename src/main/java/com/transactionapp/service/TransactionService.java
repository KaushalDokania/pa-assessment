package com.transactionapp.service;

import java.util.List;

import com.transactionapp.model.Transaction;

public interface TransactionService {

	public Boolean addTransaction(Transaction transaction);
	public List<Transaction> getAllTransactions();
	public Transaction getTransactionById(Long id);
	public Transaction getAmountById(Long id);
	public Transaction getTransactionByType(String type);
}
