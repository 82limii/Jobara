package kr.co.jobara.searchboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/searchboard")
public class SearchBoardRetrieveController {
	
	@RequestMapping("searchBoardList.do")
	public String list() {
		return "searchboard/searchboardList";
	}
	@RequestMapping("com/searchBoardList.do")
	public String listCom() {
		return "searchboard/searchboardList";
	}

	@RequestMapping("searchBoardView.do")
	public String view() {
		return "searchboard/searchboardView";
	}
	@RequestMapping("com/searchBoardView.do")
	public String viewCom() {
		return "searchboard/searchboardView";
	}
	
}
