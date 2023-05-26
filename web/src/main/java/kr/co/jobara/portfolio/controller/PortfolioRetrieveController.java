package kr.co.jobara.portfolio.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.portfolio.service.PortfolioService;
import kr.co.jobara.portfolio.vo.PortfolioVO;

@Controller
@RequestMapping("/myPage")
public class PortfolioRetrieveController {
	@Inject
	private MenuService menuService; 
	
	@Inject
	private PortfolioService service;
	
	@Inject
	private AttatchService attatchService;

	@RequestMapping(value="portfolioList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
		@SessionAttribute("authMember") PmemberVO auth 
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, Model model
	) {
		// pmemId 값 세션에서 가져오기
		PagingVO<PortfolioVO> paging = new PagingVO<>();
		String pmemId = auth.getPmemId();
		paging.setPmemId(pmemId);
		
		// 현재 페이지 처리하기
		paging.setCurrentPage(currentPage);
		
		// portfolio 리스트 가져오기
		List<PortfolioVO> portfolioList = service.retrievePortfolioList(paging);

		// 첨부파일 가져오기 
		for (PortfolioVO vo : portfolioList) {
			String attSn = vo.getAttSn();
			AttatchVO attatch = attatchService.retrieveAttatch(attSn);
			vo.setAttatch(attatch);
		}
		
		model.addAttribute("portfolioList", portfolioList);
		
		model.addAttribute("pagingVO", paging);
		
		
		return "portfolio/portfolioList";
	}
	
	@RequestMapping("/portfolioList.do")
	public String list(
			@SessionAttribute("authMember") PmemberVO auth 
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			) {
		
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		listToJson(auth, currentPage ,model);
		return "portfolio/portfolioList";
	}
	

}
