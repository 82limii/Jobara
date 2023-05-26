package kr.co.jobara.board.askit.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.board.askit.service.AskItService;
import kr.co.jobara.board.askit.vo.AskItVO;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.service.MenuService;
import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.commons.vo.MenuVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.searchboard.vo.SearchVO;

/**
 * @author 최인수
 * Ask It 게시글 조회 로직
 */
@Controller
@RequestMapping("/board")
public class AskItRetrieveController {
	
	@Inject
	private MenuService menuService;
	
	@Inject
	private AskItService service;
	
	@Inject
	private AttatchService attatchService;
	
	//게시글 전체 목록
	@RequestMapping("askItList.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("simpleSearch") SearchVO simpleSearch
			, Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("board");
		model.addAttribute("menuList", menuList);
		
		listToJson(currentPage, simpleSearch, model);
		
		return "board/askIt/askItList";
	}
	
	@RequestMapping(value="askItList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String listToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("simpleSearch") SearchVO simpleSearch
			, Model model) {
		PagingVO<AskItVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		service.retrieveAskItBoardList(paging);
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}

	//게시글 상세페이지
	@RequestMapping("askItView.do")
	public String viewList(
			@RequestParam("what") int boardSn
			,Model model) {
		List<MenuVO> menuList = menuService.retrieveMenu("board");
		model.addAttribute("menuList", menuList);
		
		AskItVO askIt = service.retrieveAskItBoard(boardSn);
		model.addAttribute("askIt", askIt);
		
		String attSn = askIt.getAttSn();
		if(attSn != null) {
			List<AttatchVO> attatchList = attatchService.retrieveAttatchList(attSn);
			model.addAttribute("attatchList", attatchList);
		}
		
		return "board/askIt/askItView";
	}
}
