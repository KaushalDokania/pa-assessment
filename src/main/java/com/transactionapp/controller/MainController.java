package com.transactionapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionapp.model.Response;
import com.transactionapp.model.Transaction;
import com.transactionapp.service.TransactionService;

@RestController
@RequestMapping("/transactionservice")
public class MainController {

	@GetMapping("/test")
	public void test() {
		System.out.println("working");
	}
	
	@Autowired
	TransactionService tscService;
	
	/* ---------- GET all transactions ------------- */
	@GetMapping("/transactions")
	public ResponseEntity<?> getAllTransactions() {
		List<Transaction> list = tscService.getAllTransactions();
		if(list==null || list.isEmpty())
			return new ResponseEntity<Response>(new Response("not found"), HttpStatus.OK);
		else
			return new ResponseEntity<List<Transaction>>(list, HttpStatus.OK);
	}
	
	/* ---------- GET transaction by id ------------- */
	@GetMapping("/transactions/{id}")
	public ResponseEntity<?> getTransactionById(@PathVariable("id") Long id) {
		Transaction t = tscService.getTransactionById(id);
		if(t != null)
			return new ResponseEntity<Transaction>(t, HttpStatus.OK);
		else
			return new ResponseEntity<Response>(new Response("not found"), HttpStatus.OK);
	}
	
	/* ---------- GET Amount by id ------------- */
	@GetMapping("/sum/{id}")
	public ResponseEntity<?> getAmountById(@PathVariable("id") Long id) {
		tscService.getAmountById(id);
		Transaction t = tscService.getTransactionById(id);
		if(t != null)
			return new ResponseEntity<Transaction>(t, HttpStatus.OK);
		else
			return new ResponseEntity<Response>(new Response("not found"), HttpStatus.OK);
	}
	
	/* ---------- ADD transaction ------------- */
	@PutMapping("/transaction/{id}")
	public ResponseEntity<Response> addTransactionById(@PathVariable("id") Long id, @RequestBody Transaction transaction) {
		transaction.setId(id);
		if(tscService.addTransaction(transaction))
			return new ResponseEntity<Response>(new Response("OK"), HttpStatus.OK);
		return new ResponseEntity<Response>(new Response("Failure"), HttpStatus.OK);
	}
	
}
