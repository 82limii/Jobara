package kr.co.jobara.board.shareit.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jobara.board.shareit.service.ShareItService;
import kr.co.jobara.board.shareit.vo.ShareItVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.searchboard.vo.SearchVO;

@Controller
@RequestMapping("/board")
public class ShareItRetrieveController {
	
	@Inject
	private MenuService menuService;

	@Inject
	private ShareItService service;
	
	@Inject
	private AttatchService attatchService;
	
	@RequestMapping("shareItList.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailSearch") ShareItVO detailSearch
			, Model model) {
		PagingVO<ShareItVO> pagingVO = listToJson(currentPage, detailSearch, model);
		model.addAttribute("pagingVO", pagingVO);
		
		List<MenuVO> menuList = menuService.retrieveMenu("board");
		model.addAttribute("menuList", menuList);
		return "board/shareIt/shareItList";
	}
	
	@RequestMapping(value="shareItList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ShareItVO> listToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, ShareItVO detailSearch
			, Model model) {
		PagingVO<ShareItVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveShareItBoardList(pagingVO);
		return pagingVO;
	}
	
	
	
	
	
	@RequestMapping("shareItView.do")
	public String viewList(
			@RequestParam("what") int rebSn
			,Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("board");
		model.addAttribute("menuList", menuList);
		
		ShareItVO shareIt = service.retrieveShareItBoard(rebSn);
		model.addAttribute("shareIt", shareIt);
		
		return "board/shareIt/shareItView";
	}
}









