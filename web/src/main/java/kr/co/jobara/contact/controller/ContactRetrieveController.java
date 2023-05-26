package kr.co.jobara.contact.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.contact.service.ContactService;
import kr.co.jobara.contact.vo.ContactVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.searchboard.vo.SearchVO;

@Controller
public class ContactRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private ContactService service;
	
	@RequestMapping("/contact/com/contactView.do")
	public String contactRetrieve(
		@RequestParam("what") int contacSn	
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		ContactVO contact = service.retrieveContact(contacSn);
		model.addAttribute("contact", contact);
		
		return "contact/contactView";
	}
	
	@RequestMapping(value="/contact/com/contactList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") ContactVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		String ememId = authMember.getEmemId();
		detailSearch.setEmemId(ememId);
		
		PagingVO<ContactVO> paging = new PagingVO<>(5,5);
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		paging.setDetailSearch(detailSearch);
		
		service.retrieveContactList(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("/contact/com/contactList.do")
	public String contactList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") ContactVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		listToJson(currentPage, authMember, detailSearch, simpleSearch, model);
		
		return "contact/contactList";
	}
}
