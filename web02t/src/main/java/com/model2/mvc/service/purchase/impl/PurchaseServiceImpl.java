package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.addPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int prodNo) throws Exception {
		return purchaseDao.getPurchase(prodNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search,String buyerId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer", buyerId);
		map.put("search", search);
		List<Purchase> list = purchaseDao.getList(map);
		int totalCount = purchaseDao.getTotalCount(map);
		
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		return map;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDao.updatePurchase(purchase);
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		List<Purchase> list = purchaseDao.getList(map);
		int totalCount = purchaseDao.getTotalCount(map);
		
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		return map;
	}

	@Override
	public void updateTranCode(Map<String, Object> map) throws Exception {
		purchaseDao.updateTranCode(map);
	}
	
}
