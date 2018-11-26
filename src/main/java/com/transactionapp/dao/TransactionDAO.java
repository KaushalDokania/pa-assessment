package com.transactionapp.dao;

import java.util.List;

import com.transactionapp.model.Transaction;

public interface TransactionDAO {

	public List<Transaction> getAllTransactions();
	public Transaction getTransactionById(Long id);
	public void addTransaction(Transaction entity);
	public Double getAmountById(Long id);
}
