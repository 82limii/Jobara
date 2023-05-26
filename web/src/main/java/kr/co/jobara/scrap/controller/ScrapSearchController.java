package kr.co.jobara.scrap.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;

@Controller
@RequestMapping("/scrap")
public class ScrapSearchController {
	@Inject
	private MenuService menuService;
	
	@RequestMapping("com/scrapSearch.do")
	public String scrapList(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("compage_com");
		model.addAttribute("menuList", menuList);
		return "scrap/scrapSearch";
	}
}
