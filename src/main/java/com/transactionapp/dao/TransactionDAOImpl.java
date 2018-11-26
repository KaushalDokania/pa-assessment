package com.transactionapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.transactionapp.model.Transaction;

@Repository
@Transactional
public class TransactionDAOImpl implements TransactionDAO {

	@PersistenceContext
	EntityManager em;
	
//	select * from (select * from transaction order by parent_id,id) tsc_stored, (select @pv :=1) initialisation where find_in_set(parent_id,@pv) and length(@pv := concat(@pv, ',', id));
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getAllTransactions() {
		String hql = "FROM Transaction as tsc ORDER BY tsc.id";
		return (List<Transaction>) em.createQuery(hql).getResultList();
	}

	@Override
	public void addTransaction(Transaction entity) {
		em.persist(entity);
	}

	@Override
	public Transaction getTransactionById(Long id) {
		return em.find(Transaction.class, id);
	}

	@Override
	public Double getAmountById(Long id) {
		
		String hql = "select sum(amount) from (select * from transaction order by parent_id,id) tsc_stored, initialisation where find_in_set(parent_id,?) and length(? :=concat(?, ',', id))";
		return (Double) em.createNativeQuery(hql).setParameter(1, id).setParameter(1, id).setParameter(1, id).getSingleResult();
//		return  (Double) em.createQuery(hql).getSingleResult();
	}


}
