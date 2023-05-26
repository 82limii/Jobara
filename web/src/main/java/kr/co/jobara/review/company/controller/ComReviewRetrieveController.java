package kr.co.jobara.review.company.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.review.company.service.ComReviewService;
import kr.co.jobara.review.company.vo.ComReviewVO;

/**
 * 작성일 2022.02.23
 * @author 김승현
 * 기업리뷰(CRUD)용 RetrieveController
 */

@Controller
@RequestMapping("/review")
public class ComReviewRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private ComReviewService service;
	
	@RequestMapping(value="reviewList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ComReviewVO> listToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model	
			, @RequestParam(value="what", required=true) String ememId
			, @ModelAttribute("detailSearch") ComReviewVO detailSearch
	) {
		PagingVO<ComReviewVO> pagingVO = new PagingVO<>(3,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retrieveComReviewList(pagingVO);
		return pagingVO;
	}
	
	@RequestMapping("reviewList.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			, @RequestParam(value="what", required=true) String ememId
			, @ModelAttribute("detailSearch") ComReviewVO detailSearch
		) {
		
		detailSearch.setEmemId(ememId);
		
		PagingVO<ComReviewVO> pagingVO = listToJson(currentPage,  model, ememId, detailSearch);
		
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		
		model.addAttribute("ememId",ememId);
		
		
		return "review/company/companyReviewList";
	}
	
	@RequestMapping(value="com/reviewList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ComReviewVO> comListToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model	
			, @ModelAttribute("detailSearch") ComReviewVO detailSearch
			) {
		PagingVO<ComReviewVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retrieveComReviewList(pagingVO);
		return pagingVO;
	}
	
	@RequestMapping("com/reviewList.do")
	public String listCom(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			, @SessionAttribute("authMember") EmemberVO auth 
			, @ModelAttribute("detailSearch") ComReviewVO detailSearch
			) {
		String ememId = auth.getEmemId();
		detailSearch.setEmemId(ememId);
		
		PagingVO<ComReviewVO> pagingVO = listToJson(currentPage,  model, ememId, detailSearch);
		
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		
		model.addAttribute("ememId",ememId);
		
		
		return "review/company/companyReviewList";
	}

}
