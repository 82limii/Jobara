package kr.co.jobara.searchboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/searchboard")
public class SearchBoardInsertController {
	@RequestMapping("searchBoardInsert.do")
	public String form() {
		return "searchboard/searchboardForm";
	}
}
