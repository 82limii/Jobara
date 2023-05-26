package kr.co.jobara.incumbent.controller;

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
import kr.co.jobara.incumbent.service.IncumbentService;
import kr.co.jobara.incumbent.vo.IncumbentVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.searchboard.vo.SearchVO;

@Controller
@RequestMapping("/incumbent")
public class IncumbentRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private IncumbentService service;
	
	@RequestMapping("com/incumbentView.do")
	public String incumbentView(
		@RequestParam("what") int empSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		IncumbentVO incumbent = service.retrieveIncumbent(empSn);
		model.addAttribute("incumbent", incumbent);
		
		return "incumbent/incumbentView";
	}
	
	@RequestMapping("com/incumbentList.do")
	public String incumbent(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") IncumbentVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		listToJson(currentPage, authMember, detailSearch, simpleSearch, model);
		
		return "incumbent/incumbentList";
	}
	
	@RequestMapping(value="com/incumbentList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @SessionAttribute(value="authMember", required=false) EmemberVO authMember
		, @ModelAttribute("detailSearch") IncumbentVO detailSearch
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model
	){
		String ememId = authMember.getEmemId();
		detailSearch.setEmemId(ememId);
		
		PagingVO<IncumbentVO> pagingVO = new PagingVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleSearch(simpleSearch);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveIncumbentList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "jsonView";
	}
	
}
