package kr.co.jobara.scrap.controller;

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
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.scrap.service.ScrapPostService;
import kr.co.jobara.scrap.vo.ScrapPostVO;

@Controller
@RequestMapping("/scrap")
public class ScrapCompanyRetrieveController {
	@Inject
	private MenuService menuService;
	@Inject
	private  ScrapPostService service;
	
	@RequestMapping("/scrapEnter.do")
	public String list(
			@SessionAttribute("authMember") PmemberVO auth 
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("scrapPost") ScrapPostVO scrapPost
			, Model model
			) {
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		listToJson(auth, currentPage, scrapPost, model);
		return "scrap/scrapEnter";
	}
	
	@RequestMapping(value="scrapEnter.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
			@SessionAttribute("authMember") PmemberVO auth 
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("scrapPost") ScrapPostVO scrapPost
			, Model model
		) {
			PagingVO<ScrapPostVO> paging = new PagingVO<>();
			String pmemId = auth.getPmemId();
			paging.setPmemId(pmemId);
			
			service.retrieveScrapCompanyList(paging);
			model.addAttribute("pagingVO", paging);
			
			return "jsonView";
	}
}
