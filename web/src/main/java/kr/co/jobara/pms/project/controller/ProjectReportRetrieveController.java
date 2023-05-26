package kr.co.jobara.pms.project.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.pms.project.service.ReportService;
import kr.co.jobara.pms.project.vo.ReportVO;

@Controller
@RequestMapping("/project")
public class ProjectReportRetrieveController {

	@Inject
	private ReportService service;
	
	@Inject
	private AttatchService attatchService;
	
	@RequestMapping("projReport.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam("what") int proSn
			, @ModelAttribute("detailSearch") ReportVO detailSearch
			, Model model) {
		listToJson(currentPage,proSn, detailSearch, model);
		return "project/projectReportList";
	}
	
	@RequestMapping(value="projReport.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String listToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam("what") int proSn
			, @ModelAttribute("detailSearch") ReportVO detailSearch
			, Model model
			) {
		PagingVO<ReportVO> pagingVO = new PagingVO<>();
		detailSearch.setProSn(proSn);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retrieveReportList(pagingVO);
		model.addAttribute("paging", pagingVO);
		return "jsonView";
	}

	@RequestMapping("projReportView.do")
	public String view(
			@RequestParam("jobara") int repoSn
			, @RequestParam("what") int proSn
			, Model model
			) {
		ReportVO report = service.retrieveReport(repoSn);
		model.addAttribute("report", report);
		
		String attSn = report.getAttSn();
		if(attSn != null) {
			List<AttatchVO> attatchList = attatchService.retrieveAttatchList(attSn);
			model.addAttribute("attatchList", attatchList);
		}
		
		
		return "project/projectReportView";
	}

	@RequestMapping("com/projReport.do")
	public String listCom(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam("what") int proSn
			, @ModelAttribute("detailSearch") ReportVO detailSearch
			, Model model) {
		listToComJson(currentPage,proSn, detailSearch, model);
		return "project/projectReportList";
	}
	
	
	@RequestMapping(value="com/projReport.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String listToComJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam("what") int proSn
			, @ModelAttribute("detailSearch") ReportVO detailSearch
			, Model model
			) {
		PagingVO<ReportVO> pagingVO = new PagingVO<>();
		detailSearch.setProSn(proSn);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retrieveReportList(pagingVO);
		model.addAttribute("paging", pagingVO);
		return "jsonView";
	}
	
	
	
}
