package kr.co.jobara.board.solveit.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;

@Controller
@RequestMapping("/board")
public class SolveItRetrieveController {
	
	@Inject
	private MenuService menuService;
	
	@RequestMapping("solveItList.do")
	public String list(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("board");
		model.addAttribute("menuList", menuList);
		return "board/solveIt/solveItList";
	}
	
	@RequestMapping("solveItView.do")
	public String viewList(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("board");
		model.addAttribute("menuList", menuList);
		return "board/solveIt/solveItView";
	}
}
