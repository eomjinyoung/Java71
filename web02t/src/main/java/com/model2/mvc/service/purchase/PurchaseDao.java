package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseDao {
	
	// INSERT
	public void addPurchase(Purchase purchase) throws Exception ;

	// SELECT ONE
	public Purchase getPurchase(int purchaseNo) throws Exception ;

	// SELECT LIST
	public List<Purchase> getList(Map<String, Object> map) throws Exception ;

	// UPDATE
	public void updatePurchase(Purchase purchase) throws Exception ;
		
	public int getTotalCount(Map<String, Object> map) throws Exception ;
	
	// UPDATE TrnaCode
	public void updateTranCode(Map<String, Object> map) throws Exception;
}