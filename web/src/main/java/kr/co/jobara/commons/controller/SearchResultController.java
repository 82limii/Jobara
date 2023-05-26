package kr.co.jobara.commons.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.member.service.EmemberService;
import kr.co.jobara.member.vo.EmemberVO;

@Controller
@RequestMapping("/search")
public class SearchResultController {
	
	@Inject
	private EmemberService service;
	
	@RequestMapping("searchResult.do")
	public String searchPage(
		@RequestParam(value="what", required=true) String searchName
		, Model model
	) {
		List<EmemberVO> searchList = service.searchResultCompany(searchName);
		model.addAttribute("searchResult", searchList);
		return "searchResult";
	}
}
