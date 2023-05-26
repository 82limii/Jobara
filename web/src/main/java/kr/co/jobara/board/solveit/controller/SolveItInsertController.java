package kr.co.jobara.board.solveit.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.MenuVO;

@Controller
public class SolveItInsertController {
	
	@Inject
	private MenuService menuService;
	
	@RequestMapping("/board/solveItInsert.do")
	public String Form(Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("board");
		model.addAttribute("menuList", menuList);
		return "board/solveIt/solveItForm";
	}
}
