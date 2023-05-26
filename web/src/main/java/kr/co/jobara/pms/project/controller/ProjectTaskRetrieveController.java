package kr.co.jobara.pms.project.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.pms.project.service.ProTaskService;
import kr.co.jobara.pms.project.vo.ProTaskVO;

@Controller
@RequestMapping("/project")
public class ProjectTaskRetrieveController {
	
	@Inject
	private ProTaskService service;
	
	@RequestMapping("projTaskList.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam("what") int proSn
			, @ModelAttribute("detailSearch") ProTaskVO detailSearch
			, Model model) {
		
		listToJson(currentPage, proSn, detailSearch, model);
		
		return "project/projectTaskList";
	}
	
	@RequestMapping(value="projTaskList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String listToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam("what") int proSn
			, @ModelAttribute("detailSearch") ProTaskVO detailSearch
			, Model model) {
		PagingVO<ProTaskVO> pagingVO = new PagingVO<>();
		detailSearch.setProSn(proSn);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retrieveTaskBoardList(pagingVO);
		model.addAttribute("paging",pagingVO);
		return "jsonView";
	
	}
	
	@RequestMapping("projTaskView.do")
	public String view(
			@RequestParam("jobara") int protSn
			, @RequestParam("what") int proSn
			,Model model
			) {
		ProTaskVO task = service.retrieveTaskBoard(protSn);
		task.setProSn(proSn);
		model.addAttribute("task", task);
		
		return "project/projectTaskView";
	}
	
	
	
	@RequestMapping("com/projTaskList.do")
	public String listCom() {
		return "project/projectTaskList";
	}
}
