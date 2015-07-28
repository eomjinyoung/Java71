package com.model2.mvc.web.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public PurchaseController() {}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping
	public ModelAndView addPurchaseView(@RequestParam("prod_no") int prodNo,
			HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Product product = productService.getProduct(prodNo);
		User user = (User)session.getAttribute("user");
		
		mav.setViewName("forward:/purchase/addPurchaseView.jsp");
		mav.addObject("product", product);
		mav.addObject("user", user);
		
		return mav;
	}
	@RequestMapping
	public ModelAndView addPurchase(@ModelAttribute("purchase") Purchase purchase,
			@RequestParam("prodNo") int prodNo,
			@RequestParam("buyerId") String buyerId) throws Exception {
		System.out.println(purchase);
		System.out.println(prodNo);
		Product product = new Product();
		product.setProdNo(prodNo);
		User user = new User();
		user.setUserId(buyerId);
		
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setTranCode("1");
		purchaseService.addPurchase(purchase);
		
		product = productService.getProduct(prodNo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("product", product);
		mav.setViewName("forward:/product/getProduct.jsp");	//���ſϷ� ������
		return mav;
	}
	
	@RequestMapping
	public ModelAndView getPurchaseList(@ModelAttribute("search") Search search,
			HttpSession session) throws Exception {
		String buyerId = ((User)session.getAttribute("user")).getUserId();
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getPurchaseList(search, buyerId);
		Page resultPage = new Page(search.getCurrentPage(), (Integer)map.get("totalCount"), pageUnit, pageSize);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/purchase/listPurchase.jsp");
		mav.addObject("list", map.get("list"));
		mav.addObject("resultPage", resultPage);
		mav.addObject("search", search);
		
		return mav;
	}
	
	@RequestMapping
	public ModelAndView getSaleList(@ModelAttribute("search") Search search) throws Exception {
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getSaleList(search);		
		Page resultPage = new Page(search.getCurrentPage(), (Integer)map.get("totalCount"), pageUnit, pageSize);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/purchase/listSale.jsp");
		mav.addObject("list", map.get("list"));
		mav.addObject("search", search);
		mav.addObject("resultPage", resultPage);
		return mav;
	}
	
	@RequestMapping
	public ModelAndView updatePurchaseView(@RequestParam("tranNo") int tranNo) throws Exception {
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("purchase", purchase);
		mav.setViewName("forward:/purchase/updatePurchaseView.jsp");
		
		return mav;
	}
	@RequestMapping
	public ModelAndView updatePurchase(@ModelAttribute("purchase") Purchase purchase) throws Exception {
		purchaseService.updatePurchase(purchase);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/app/purchase/getPurchaseList");
		return mav;		
	}
	
	@RequestMapping
	public ModelAndView updateTranCodeByProd(@RequestParam("prodNo") int prodNo,
			@RequestParam("tranCode") String tranCode) throws Exception {
		Purchase purchase = new Purchase();
		Product product = new Product();
		product.setProdNo(prodNo);
		purchase.setPurchaseProd(product);
		purchase.setTranCode(tranCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", purchase);
		purchaseService.updateTranCode(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/app/purchase/getSaleList?menu=manage");
		return mav;
	}
	@RequestMapping
	public ModelAndView updateTranCode (@RequestParam("tranNo") int tranNo,
			@RequestParam("tranCode") String tranCode) throws Exception {
		Purchase purchase = new Purchase();
		purchase.setTranNo(tranNo);
		purchase.setTranCode(tranCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purchase", purchase);
		purchaseService.updateTranCode(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/app/purchase/getPurchaseList?menu=search");
		return mav;
	}
}