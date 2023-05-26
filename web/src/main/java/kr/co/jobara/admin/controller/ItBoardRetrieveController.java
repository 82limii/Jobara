package kr.co.jobara.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class ItBoardRetrieveController {

	@RequestMapping("admin/itBoardList.do")
	public String itBoardList() {
		return "admin/board/itBoardList";
	}
	
	@RequestMapping("admin/askItView.do")
	public String askItView() {
		return "admin/board/askItView";
	}
	@RequestMapping("admin/shareItView.do")
	public String shareItView() {
		return "admin/board/shareItView";
	}
}
