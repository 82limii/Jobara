package kr.co.jobara.specDivi.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;

@Controller
@RequestMapping("/review")
public class SpecDiviRetrieveController {
	@Inject
	private MenuService menuService;
	
	@RequestMapping("specDivisionList.do")
	public String list(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_per");
		model.addAttribute("menuList", menuList);
		return "review/spec/specDiviList";
	}
	@RequestMapping("com/specDivisionList.do")
	public String listCom(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		return "review/spec/specDiviList";
	}
	
//	@RequestMapping("/specDiviView.do")
//	public String spec() {
//		return "review/spec/specDiviView";
//	}
}
