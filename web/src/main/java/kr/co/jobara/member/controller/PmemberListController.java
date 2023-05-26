package kr.co.jobara.member.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.service.PmemberService;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.searchboard.vo.SearchVO;

/**
 * 장인슬
 * @author PC-17
 * 2022-03-03
 */
@Controller
@RequestMapping("/member/admin")
public class PmemberListController {
	
	@Inject
	private PmemberService service;
	
	@RequestMapping(value="pmemberList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
			@ModelAttribute("simpleSearch") SearchVO simpleSearch
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			) {
		PagingVO<PmemberVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleSearch(simpleSearch);
		
		service.retrievePmemberList(pagingVO);
		model.addAttribute("pagingVO", pagingVO);
		return "jsonView";
	}
	
	@RequestMapping("pmemberList.do")
	public String pmemberList(@ModelAttribute("simpleSearch") SearchVO simpleSearch
							, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
							, Model model) {
		
		listToJson(simpleSearch, currentPage, model);
		return "admin/member/pMemberList";
	}
	
	@RequestMapping("pmemberView.do")
	public String view(@RequestParam("what") String pmemId, Model model) {
		
		PmemberVO pmemberList = service.retrievePmember(pmemId);
		model.addAttribute("pmemberList", pmemberList);
		
		return "admin/member/pMyPage";
	}
}
