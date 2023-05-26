package kr.co.jobara.resume.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;

@Controller
@RequestMapping("/myPage")
public class PositionRetrieveController {
	
	@Inject
	private MenuService menuService; 

	@RequestMapping("positionList.do")
	public String list(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("mypage_per");
		model.addAttribute("menuList", menuList);
		return "resume/positionList";
	}
}
