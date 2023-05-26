package kr.co.jobara.pms.project.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.pms.project.service.ProNoticeService;
import kr.co.jobara.pms.project.vo.ProNoticeVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/project")
@Slf4j
public class ProjectNoticeRetrieveController {

	@Inject
	private ProNoticeService service;
	
	@Inject
	private AttatchService attatchService;
	
	@RequestMapping(value="projNotice.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listToJson(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam("what") int proSn
			, @ModelAttribute("detailSearch") ProNoticeVO detailSearch
			,Model model
			) {
		PagingVO<ProNoticeVO> pagingVO = new PagingVO<>();
		detailSearch.setProSn(proSn);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		service.retrieveNoticeList(pagingVO);
		model.addAttribute("paging", pagingVO);
		return "jsonView";
	}
	
	
	@RequestMapping("projNotice.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam("what") int proSn
			, @ModelAttribute("detailSearch") ProNoticeVO detailSearch
			,Model model) {
		
		listToJson(currentPage, proSn, detailSearch, model);
		
		return "project/projectNoticeList";
	}
	
	@RequestMapping("projNoticeView.do")
	public String view(
			@RequestParam("jobara") int notiSn
			, @RequestParam("what") int proSn
			, Model model) {
		
		ProNoticeVO notice = service.retrieveNotice(notiSn);
		model.addAttribute("notice",notice);
		
		String attSn = notice.getAttSn();
		if(attSn != null) {
			List<AttatchVO> attatchList = attatchService.retrieveAttatchList(attSn);
			model.addAttribute("attatchList", attatchList);
		}
		
		return "project/projectNoticeView";
		
	}
	
	@RequestMapping("com/projNotice.do")
	public String listCom() {
		return "project/projectNoticeList";
	}
}
