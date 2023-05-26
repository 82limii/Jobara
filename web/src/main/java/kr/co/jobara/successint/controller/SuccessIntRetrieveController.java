package kr.co.jobara.successint.controller;

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
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.successint.service.SuccessIntService;
import kr.co.jobara.successint.vo.SuccessIntVO;

@Controller
@RequestMapping("/successInt")
public class SuccessIntRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private SuccessIntService service;

	@RequestMapping(value="successIntList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<SuccessIntVO> listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, Model model	
		, @RequestParam(value="what", required=true) String ememId
		, @ModelAttribute("detailSearch") SuccessIntVO detailSearch
	) {
		
		PagingVO<SuccessIntVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveSuccessIntList(pagingVO);
		return pagingVO;
	}
	
	@RequestMapping("/successIntList.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			, @RequestParam(value="what", required=true) String ememId
			, @ModelAttribute("detailSearch") SuccessIntVO detailSearch
		) {
		
			detailSearch.setEmemId(ememId);
		
			PagingVO<SuccessIntVO> pagingVO = listToJson(currentPage,  model, ememId, detailSearch);
			model.addAttribute("pagingVO", pagingVO);
			List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
			model.addAttribute("menuList", menuList);
			
			model.addAttribute("ememId",ememId);
			
			return "successInt/successIntList";
	}
	
	@RequestMapping(value="com/successIntList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<SuccessIntVO> ComlistToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model	
			
			, @ModelAttribute("detailSearch") SuccessIntVO detailSearch
			) {
		
		PagingVO<SuccessIntVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveSuccessIntList(pagingVO);
		return pagingVO;
	}
	
	@RequestMapping("/com/successIntList.do")
	public String listCom(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			, @ModelAttribute("detailSearch") SuccessIntVO detailSearch
			, @SessionAttribute("authMember") EmemberVO auth
			) {
		String ememId = auth.getEmemId();
		detailSearch.setEmemId(ememId);
		
		PagingVO<SuccessIntVO> pagingVO = listToJson(currentPage,  model, ememId, detailSearch);
		model.addAttribute("pagingVO", pagingVO);
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		model.addAttribute("ememId",ememId); 
		
		return "successInt/successIntList";
	}
	
	
	@RequestMapping("/successIntView.do")
	public String view(
		@RequestParam("what") int sinSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		SuccessIntVO successInt = service.retrieveSuccessInt(sinSn);
		model.addAttribute("successInt", successInt);
		
		return "successInt/successIntView";
	}
	@RequestMapping("com/successIntView.do")
	public String viewCom(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		return "successInt/successIntView";
	}
}
