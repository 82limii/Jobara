package kr.co.jobara.member.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.service.EmemberService;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.searchboard.vo.SearchVO;

/**
 * 장인슬
 * @author PC-17
 * 2022-03-03
 */
@Controller
@RequestMapping("/member/admin")
public class EmemberListController {
	
	@Inject
	private EmemberService service;
	
	
	@RequestMapping(value="ememberList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
			@ModelAttribute("simpleSearch") SearchVO simpleSearch
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			) {
		PagingVO<EmemberVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleSearch(simpleSearch);
		
		service.retrieveEmemberList(pagingVO);
		model.addAttribute("pagingVO", pagingVO);
		return "jsonView";
	}
	
	
	@RequestMapping("ememberList.do")
	public String ememberList(@ModelAttribute("simpleSearch") SearchVO simpleSearch
							, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
							, Model model) {
		
		listToJson(simpleSearch, currentPage, model);
		
		return "admin/member/eMemberList";
	}
	
	@RequestMapping("ememberView.do")
	public String view(@RequestParam("what") String ememId
						, Model model) {
		
		EmemberVO ememberList = service.retrieveEmember(ememId);
		model.addAttribute("ememberList", ememberList);
		
		return "admin/member/eMyPage";
	}
}
