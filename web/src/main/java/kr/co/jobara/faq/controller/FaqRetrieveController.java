package kr.co.jobara.faq.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.faq.service.FaqService;
import kr.co.jobara.faq.vo.FaqVO;
import kr.co.jobara.searchboard.vo.SearchVO;

@Controller
@RequestMapping("/faq")
public class FaqRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private FaqService service;
	
	@RequestMapping(value="faqList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		PagingVO<FaqVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveFaqList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("faqList.do")
	public String faqList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_per");
		model.addAttribute("menuList", menuList);
		
		listToJson(currentPage, simpleSearch, model);
		
		return "faq/faqList";
	}
	
	@RequestMapping(value="com/faqList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String comListToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		PagingVO<FaqVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveFaqList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("com/faqList.do")
	public String faqListCom(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("cscenter_com");
		model.addAttribute("menuList", menuList);
		
		comListToJson(currentPage, simpleSearch, model);
		
		return "faq/faqList";
	}
	
	@RequestMapping(value="admin/faqList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adminListToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		PagingVO<FaqVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveFaqList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("admin/faqList.do")
	public String faqListAdmin(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		adminListToJson(currentPage, simpleSearch, model);
		
		return "admin/cs/faqList";
	}
	
}
