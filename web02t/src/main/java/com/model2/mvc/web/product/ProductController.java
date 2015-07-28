package com.model2.mvc.web.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println("::: ProductController default Constructor call...");
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	@RequestMapping
	public String addProduct(@ModelAttribute("product") Product product) throws Exception {
		productService.addProduct(product);
		return "redirect:/app/product/getProductList?menu=manage";
	}
	
	@RequestMapping
	public String getProductList(@ModelAttribute("search") Search search, Model model) throws Exception {
		
		System.out.println(search);
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = productService.getProductList(search);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")), pageUnit, pageSize);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProductView.jsp";
	}
	
	@RequestMapping
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model,
			HttpSession session,
			HttpServletRequest request) throws Exception {
		
		Product product = productService.getProduct(prodNo);
		if(product.getProTranCode() == null) {
			product.setProTranCode("0");
		}
		System.out.println(product);
		model.addAttribute("product", product);

		List<Integer> history = null;
		if((history = (List)session.getAttribute("history")) == null) {
			session = request.getSession();
			history = new ArrayList<Integer>();
		}
		history.add(0,product.getProdNo());
		session.setAttribute("history", history);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping
	public String updateProductView(@RequestParam("prodNo") int prodNo,Model model) throws Exception {
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		return "forward:/product/updateProductView.jsp";
	}
	@RequestMapping
	public String updateProduct(@ModelAttribute("product") Product product) throws Exception {
		productService.updateProduct(product);
		return "forward:/product/updateProductView.jsp";
	}
}
