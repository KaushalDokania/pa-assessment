package com.transactionapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionapp.dao.TransactionDAO;
import com.transactionapp.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionDAO tscDao;
	
	@Override
	public Boolean addTransaction(Transaction transaction) {
		System.out.println("adding transaction");
		if(transaction.getParentId() != null) {
			transaction.setParent(tscDao.getTransactionById(transaction.getParentId()));
		}
		tscDao.addTransaction(transaction);
		return true;
	}

	@Override
	public Transaction getTransactionById(Long id) {
		return tscDao.getTransactionById(id);
	}

	@Override
	public Transaction getTransactionByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() {
//		System.out.println("sum = " + tscDao.getAmountById(1L));
		return tscDao.getAllTransactions();
	}

	@Override
	public Transaction getAmountById(Long id) {
		Transaction tsc = tscDao.getTransactionById(id);
		System.out.println("sum = " + getChildrenAmount(tsc));
		return null;
	}

	private Double getChildrenAmount(Transaction tsc) {
		Double sum = tsc.getAmount();
		if(! tsc.getChildren().isEmpty() ) {
			for(Transaction child : tsc.getChildren())
				sum += getChildrenAmount(child);
		}
		return sum;
	}
}
