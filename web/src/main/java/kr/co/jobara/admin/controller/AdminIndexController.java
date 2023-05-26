package kr.co.jobara.admin.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jobara.admin.service.LogService;
import kr.co.jobara.admin.vo.LogVO;
import kr.co.jobara.commons.vo.PagingVO;

@Controller
public class AdminIndexController {
	
	@Inject
	private LogService logService;
	
	@RequestMapping("/indexAdmin.do")
	public String indexAdmin(
			Model model
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
	) {
		PagingVO<LogVO> paging = listToJson(currentPage);
		model.addAttribute("paging", paging);

		return "indexAdmin";
	}

	@RequestMapping(value="/indexAdmin.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	private PagingVO<LogVO> listToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
	) {
		PagingVO<LogVO> paging = new PagingVO<>(20, 5);
		paging.setCurrentPage(currentPage);
		logService.retrieveLogList(paging);
		
		return paging;
	}
}
