package kr.co.jobara.review.coint.controller;

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
import kr.co.jobara.review.coint.service.CointService;
import kr.co.jobara.review.coint.vo.CointVO;

/**
 * 작성일 2022.02.28
 * @author 김승현
 * 리뷰(CRUD)용 RetrieveController
 */

@Controller
@RequestMapping("/review")
public class CotintReviewRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private CointService service;
	
	@RequestMapping(value="cointReviewList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<CointVO> listToJson(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, Model model	
		, @RequestParam(value="what", required=true) String ememId
		, @ModelAttribute("detailSearch") CointVO detailSearch
	) {
//		detailSearch.setEmemId(ememId);
//		PagingVO<CointVO> paging = new PagingVO<>();
//		paging.setCurrentPage(currentPage);
//		paging.setPmemId(ememId);
//		
//		service.retrieveCointList(paging);
//		
//		model.addAttribute("pagingVO", paging);
		PagingVO<CointVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retrieveCointList(pagingVO);
		return pagingVO;
	}
	
	@RequestMapping("cointReviewList.do")
	public String List(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, Model model
		, @RequestParam(value="what", required=true) String ememId
		, @ModelAttribute("detailSearch") CointVO detailSearch
	) {
		detailSearch.setEmemId(ememId);
		
		PagingVO<CointVO> pagingVO = listToJson(currentPage,  model, ememId, detailSearch);
		
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		model.addAttribute("ememId",ememId);
		
		
		return "review/cointReviewList";
	}
	
	@RequestMapping(value="com/cointReviewList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<CointVO> comListToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model	
			, @ModelAttribute("detailSearch") CointVO detailSearch
			) {
		PagingVO<CointVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retrieveCointList(pagingVO);
		return pagingVO;
	}
	
	@RequestMapping("com/cointReviewList.do")
	public String comList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			, @SessionAttribute("authMember") EmemberVO auth
			, @ModelAttribute("detailSearch") CointVO detailSearch
			) {
		String ememId = auth.getEmemId();
		detailSearch.setEmemId(ememId);
		
		PagingVO<CointVO> pagingVO = listToJson(currentPage,  model, ememId, detailSearch);
		
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		model.addAttribute("ememId",ememId);
		
		
		return "review/cointReviewList";
	}
}
