package kr.co.jobara.review.cote.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.review.cote.service.CoteService;
import kr.co.jobara.review.cote.vo.CoteVO;

@Controller
@RequestMapping("/review")
public class CoteReviewRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private CoteService service;
	
	
	@RequestMapping("coteReviewView.do")
	public String view(
			@RequestParam("what") int coteSn
			, Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		CoteVO cote = service.retrieveCote(coteSn);
		model.addAttribute("cote", cote);
		return "review/cote/coteReviewView";
	}
	
	@RequestMapping(value="coteReviewView.do", headers="X-Requested-With=XMLHttpRequest")
	public String ViewForModal(
			@RequestParam("what") int coteSn
			, Model model){
		CoteVO cote = service.retrieveCote(coteSn);
		model.addAttribute("cote", cote);
		return "review/cote/coteReviewView";
		
	}
	
	@RequestMapping("com/coteReviewView.do")
	public String viewCom(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		return "review/cote/coteReviewView";
	}
}
