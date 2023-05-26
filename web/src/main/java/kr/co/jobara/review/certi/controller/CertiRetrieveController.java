package kr.co.jobara.review.certi.controller;

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
import kr.co.jobara.review.certi.service.CertiService;
import kr.co.jobara.review.certi.vo.CertiVO;
import kr.co.jobara.searchboard.vo.SearchVO;

/**
 * 작성일 2022.03.04
 * @author 김승현
 * 리뷰인증(CRUD)용 RetrieveController
 */

@Controller
@RequestMapping("/review")
public class CertiRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private CertiService service;

	@RequestMapping(value="certiList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleSearch") SearchVO simpleSearch
		, Model model	
	) {
		PagingVO<CertiVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveCertiList(paging);
		
		model.addAttribute("pagingVO", paging);
		
		return "jsonView";
	}
	
	@RequestMapping("/certiList.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("simpleSearch") SearchVO simpleSearch
			, Model model
		) {
			List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
			model.addAttribute("menuList", menuList);
			
			listToJson(currentPage, simpleSearch, model);
			
			return "review/certi/certiList";
	}
	
	@RequestMapping("/certiView.do")
	public String view(
		@RequestParam("what") int certiSn
		, Model model
	) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		CertiVO certi = service.retrieveCerti(certiSn);
		model.addAttribute("certi", certi);
		
		return "review/certi/certiView";
	}
}
