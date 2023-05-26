package kr.co.jobara.apply.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.apply.service.ApplyService;
import kr.co.jobara.apply.vo.ApplyVO;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.searchboard.vo.SearchVO;

@Controller
@RequestMapping("/myPage")
public class ApplyRetrieveController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private ApplyService service;

	@RequestMapping(value="applyList.do", method=RequestMethod.GET)
	public String list(
			@SessionAttribute(value="authMember", required=false) PmemberVO pmem
			,@ModelAttribute("simpleSearch") SearchVO simpleSearch
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model) {
		//메뉴 불러오기
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		
		
		listToJson(simpleSearch, pmem,currentPage, model);
		return "apply/applyList";
	}
	
	@RequestMapping(value="applyList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
			@ModelAttribute("simpleSearch") SearchVO simpleSearch
			, @SessionAttribute(value="authMember", required=false) PmemberVO pmem
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			) {
		PagingVO<ApplyVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleSearch(simpleSearch);
		pagingVO.setPmemId(pmem.getPmemId());
		PagingVO<ApplyVO> pagingVO1 = new PagingVO<>();
		pagingVO1.setCurrentPage(currentPage);
		pagingVO1.setSimpleSearch(simpleSearch);
		pagingVO1.setPmemId(pmem.getPmemId());
		
		
		service.retrieveProList(pagingVO);
		model.addAttribute("pagingVO1", pagingVO);
		
		service.retrieveJobList(pagingVO1);
		model.addAttribute("pagingVO2", pagingVO1);
		return "jsonView";
	}
	

}
