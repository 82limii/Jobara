package kr.co.jobara.pms.project.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.contact.service.ContactService;
import kr.co.jobara.contact.vo.ContactVO;
import kr.co.jobara.searchboard.vo.SearchVO;

@Controller
@RequestMapping("/project")
public class ProjectNumRetrieveController {
	
	@Inject
	private ContactService service;
	
	@RequestMapping("/com/projPhoneNumView.do")
	public String contactRetrieveCom(
		@RequestParam("what") int contacSn	
		, Model model
	) {
		ContactVO contact = service.retrieveContact(contacSn);
		model.addAttribute("contact", contact);
		
		return "project/projectNumView";
	}
	
	@RequestMapping("/projPhoneNumView.do")
	public String contactRetrieve(
			@RequestParam("what") int contacSn	
			, Model model
			) {
		ContactVO contact = service.retrieveContact(contacSn);
		model.addAttribute("contact", contact);
		
		return "project/projectNumView";
	}
	
	@RequestMapping(value="/com/projPhoneNum.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJsonCom(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @RequestParam("what") int proSn
		, @ModelAttribute("detailSearch") ContactVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		detailSearch.setProSn(proSn);
		
		PagingVO<ContactVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		paging.setDetailSearch(detailSearch);
		
		service.retrieveContactListPro(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("/com/projPhoneNum.do")
	public String contactListCom(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @RequestParam("what") int proSn
		, @ModelAttribute("detailSearch") ContactVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		listToJsonCom(currentPage, proSn, detailSearch, simpleSearch, model);
		
		return "project/projectNumListCom";
	}
	
	@RequestMapping(value="/projPhoneNum.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @RequestParam("what") int proSn
		, @ModelAttribute("detailSearch") ContactVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		detailSearch.setProSn(proSn);
		
		PagingVO<ContactVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		paging.setDetailSearch(detailSearch);
		
		service.retrieveContactListPro(paging);
		
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("/projPhoneNum.do")
	public String contactList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam("what") int proSn
			, @ModelAttribute("detailSearch") ContactVO detailSearch
			, @ModelAttribute("simpleSearch") SearchVO simpleSearch
			, Model model
			) {
		listToJson(currentPage, proSn, detailSearch, simpleSearch, model);
		
		return "project/projectNumList";
	}
	
}
