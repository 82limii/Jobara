package kr.co.jobara.review.inter.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.review.inter.service.InterService;
import kr.co.jobara.review.inter.vo.InterVO;

@Controller
@RequestMapping("/review")
public class InterReviewRetrieveController {
	@Inject
	private MenuService menuService;
	
	@Inject
	private InterService service;
	
	@RequestMapping("interReviewView.do")
	public String view(
			@RequestParam("what") int interSn
			, Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		InterVO inter = service.retrieveInter(interSn);
		model.addAttribute("inter", inter);
		return "review/inter/interReviewView";
	}
	@RequestMapping("com/interReviewView.do")
	public String viewCom(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		return "review/inter/interReviewView";
	}
}
